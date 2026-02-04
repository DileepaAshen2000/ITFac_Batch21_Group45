package admin.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.junit.Assert.*;

public class TC_ADM_API_CAT_03_Steps extends BaseApi {

    String subCategoryName;

    @When("Admin sends POST request to create sub category")
    public void admin_sends_post_request_to_create_sub_category() {

        subCategoryName =
                "SUB_" + UUID.randomUUID().toString().substring(0, 6);

        int parentCategoryId =
                TC_ADM_API_CAT_02_Steps.createdCategoryId;

        String body = "{\n" +
                "  \"name\": \"" + subCategoryName + "\",\n" +
                "  \"parentId\": " + parentCategoryId + "\n" +
                "}";

        AdminCommonSteps.response =
                request.body(body).post("/api/categories");
    }

    @Then("response should contain created sub category details")
    public void response_should_contain_created_sub_category_details() {

        assertNotNull(AdminCommonSteps.response.jsonPath().get("id"));
        assertEquals(subCategoryName,
                AdminCommonSteps.response.jsonPath().getString("name"));
    }
}
