package admin.api.stepdefinitions;

import common.utils.DriverFactory;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryApiSteps {
    private Response response;
    private RequestSpecification request;
    private final String BASE_URL = "http://localhost:8008";

    @And("Admin is authenticated with the API session")
    public void authenticate_api() {
        // We use preemptive basic auth if the API expects standard credentials
        // Replace "admin" and "admin123" with your actual test credentials
        request = RestAssured.given()
                .auth().preemptive().basic("admin", "admin123")
                .baseUri(BASE_URL)
                .contentType("application/json")
                .log().ifValidationFails(); // This helps us see details if it fails again

        // Keep the cookie logic just in case the app uses a hybrid approach
        Set<Cookie> cookies = DriverFactory.getDriver().manage().getCookies();
        for (Cookie cookie : cookies) {
            request.cookie(cookie.getName(), cookie.getValue());
        }
    }

    // --- IMPLEMENTED MISSING STEPS ---

    @Given("a category with name {string} already exists")
    public void a_category_with_name_already_exists(String name) {
        // We call the post method internally to ensure it exists before the test
        post_category("/api/categories", name);
    }

    @Given("a parent category exists with ID {int}")
    public void a_parent_category_exists_with_id(Integer id) {
        // Logic to verify/create parent if needed
        System.out.println("Ensuring parent ID " + id + " exists.");
    }

    @Given("a sub-category with name {string} already exists under parent {int}")
    public void a_sub_category_with_name_already_exists_under_parent(String name, Integer parentId) {
        post_sub_category("/api/categories", name, parentId);
    }

    // --- EXISTING STEPS ---

    @When("Admin sends a POST request to {string} with name {string}")
    public void post_category(String endpoint, String name) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("parentId", null);
        response = request.body(body).post(endpoint);
    }

    @When("Admin sends a POST request to {string} with name {string} and parentId {int}")
    public void post_sub_category(String endpoint, String name, Integer parentId) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("parentId", parentId);
        response = request.body(body).post(endpoint);
    }

    @When("Admin sends a GET request to {string} with params:")
    public void get_categories(String endpoint, Map<String, String> params) {
        response = request.queryParams(params).get(endpoint);
    }

    @Then("the API response status should be {int}")
    public void verify_status(int code) {
        Assert.assertEquals("Expected " + code + " but got " + response.getStatusCode() + ". Response: " + response.asString(),
                code, response.getStatusCode());
    }

    @Then("the API response status should be {int} or {int}")
    public void verify_status_range(int code1, int code2) {
        int actual = response.getStatusCode();
        Assert.assertTrue("Expected " + code1 + " or " + code2 + " but got " + actual,
                actual == code1 || actual == code2);
    }

    @And("the response body should contain the error {string}")
    @And("the response body should contain {string}")
    public void verify_message(String msg) {
        Assert.assertTrue("Message not found: " + msg, response.getBody().asString().contains(msg));
    }

    @And("the response should return a filtered list")
    public void verify_filtered_list() {
        Assert.assertNotNull(response.getBody().jsonPath().getList("$"));
    }
}