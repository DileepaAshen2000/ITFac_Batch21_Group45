package user.api.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserCategoryApiSteps {

    private Response response;
    private RequestSpecification request;

    @Given("User is authorized with a valid token")
    public void user_is_authorized_with_a_valid_token() {
        RestAssured.baseURI = "http://localhost:8008/api";

        request = RestAssured.given()
                .auth()
                .preemptive()
                .basic("testuser", "test1234") // Ensure password matches your system (test1234 vs test123)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");
    }

    @When("User calls {string} request to {string}")
    public void call_api(String method, String endpoint) {
        response = request.when().get(endpoint);
    }

    @When("User calls {string} request to {string} with parameter {string} as {string}")
    public void call_api_with_param(String method, String endpoint, String param, String value) {
        // Clear previous params and set new one
        response = request.queryParam(param, value).when().get(endpoint);
    }

    @Then("Response status code should be {int}")
    public void verify_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("Response should contain a list of all categories")
    public void verify_list_not_empty() {
        // Use "size()" to check the root array size
        response.then().body("size()", greaterThan(0));
    }

    @Then("All returned categories should have name containing {string}")
    public void verify_search_results(String expectedName) {
        List<String> names = response.jsonPath().getList("name");
        for (String name : names) {
            assertTrue("Category name '" + name + "' does not contain '" + expectedName + "'",
                    name.toLowerCase().contains(expectedName.toLowerCase()));
        }
    }

    // NEW: Implementation for the missing Parent Category step
    @Then("All returned categories should belong to parent {string}")
    public void all_returned_categories_should_belong_to_parent(String parentName) {
        List<String> parents = response.jsonPath().getList("parent.name");
        for (String p : parents) {
            // If parent is null, this might need logic based on your API's null-handling
            assertNotNull("Category has no parent, but expected: " + parentName, p);
            assertEquals(parentName, p);
        }
    }

    @Then("Categories should be sorted numerically by ID")
    public void verify_id_sorting() {
        List<Integer> ids = response.jsonPath().getList("id");
        List<Integer> sortedIds = ids.stream().sorted().collect(Collectors.toList());
        assertEquals("IDs are not sorted correctly", sortedIds, ids);
    }

    @Then("Categories should be sorted alphabetically by Name")
    public void verify_name_sorting() {
        List<String> names = response.jsonPath().getList("name");
        List<String> sortedNames = names.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        assertEquals("Names are not sorted alphabetically", sortedNames, names);
    }
}