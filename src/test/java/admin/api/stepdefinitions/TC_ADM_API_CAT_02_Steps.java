package admin.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.junit.Assert.*;

public class TC_ADM_API_CAT_02_Steps extends BaseApi {

    public static int createdCategoryId;
    String categoryName;

    @When("Admin sends POST request to create main category")
    public void admin_sends_post_request_to_create_main_category() {

        categoryName = "CAT_" + UUID.randomUUID().toString().substring(0, 6);

        String body = "{\n" +
                "  \"name\": \"" + categoryName + "\",\n" +
                "  \"parentId\": 0\n" +
                "}";

        AdminCommonSteps.response =
                request.body(body).post("/api/categories");
    }

    @Then("response should contain created category details")
    public void response_should_contain_created_category_details() {

        createdCategoryId =
                AdminCommonSteps.response.jsonPath().getInt("id");

        assertTrue(createdCategoryId > 0);
        assertEquals(categoryName,
                AdminCommonSteps.response.jsonPath().getString("name"));
    }
}
