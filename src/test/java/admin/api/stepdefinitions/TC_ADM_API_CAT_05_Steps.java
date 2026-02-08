package admin.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class TC_ADM_API_CAT_05_Steps extends BaseApi {

    private int categoryId;
    private String originalName;
    private String updatedName;

    @And("Admin has an existing category to update")
    public void admin_has_an_existing_category_to_update() {

        originalName = "CAT_" + UUID.randomUUID().toString().substring(0, 5);

        String createBody = "{\n" +
                "  \"name\": \"" + originalName + "\",\n" +
                "  \"parentId\": 0\n" +
                "}";

        AdminCommonSteps.response =
                request.body(createBody)
                        .post("/api/categories");

        assertEquals(201, AdminCommonSteps.response.getStatusCode());

        categoryId =
                AdminCommonSteps.response.jsonPath().getInt("id");

        assertTrue(categoryId > 0);

        System.out.println("Created category for update:");
        System.out.println("ID = " + categoryId);
        System.out.println("Name = " + originalName);
    }

    @When("Admin sends PUT request to update the category")
    public void admin_sends_put_request_to_update_the_category() {

        updatedName = "UPD_" + UUID.randomUUID().toString().substring(0, 5);

        String updateBody = "{\n" +
                "  \"name\": \"" + updatedName + "\"\n" +
                "}";

        AdminCommonSteps.response =
                request.body(updateBody)
                        .put("/api/categories/" + categoryId);
    }

    @Then("response should contain updated category details")
    public void response_should_contain_updated_category_details() {

        assertEquals(200, AdminCommonSteps.response.getStatusCode());

        int responseId =
                AdminCommonSteps.response.jsonPath().getInt("id");

        String responseName =
                AdminCommonSteps.response.jsonPath().getString("name");

        assertEquals(categoryId, responseId);
        assertEquals(updatedName, responseName);
        assertNotEquals(originalName, responseName);
    }

    @Then("updated category should reflect in category list")
    public void updated_category_should_reflect_in_category_list() {

        AdminCommonSteps.response =
                request.get("/api/categories");

        assertEquals(200, AdminCommonSteps.response.getStatusCode());

        List<Map<String, Object>> categories =
                AdminCommonSteps.response.jsonPath().getList("$");

        boolean found = categories.stream()
                .anyMatch(cat ->
                        ((Number) cat.get("id")).intValue() == categoryId
                                && updatedName.equals(cat.get("name"))
                );

        assertTrue("Updated category not found in list", found);

        System.out.println("Update verified in category list");
        System.out.println("ID = " + categoryId + ", Name = " + updatedName);
    }
}
