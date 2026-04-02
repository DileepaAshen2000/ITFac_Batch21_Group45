package admin.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TC_ADM_API_CAT_04_Steps extends BaseApi {

    int categoryIdToDelete;

    @And("Admin has an existing category to delete")
    public void admin_has_an_existing_category_to_delete() {


        categoryIdToDelete = TC_ADM_API_CAT_02_Steps.createdCategoryId;

        assertTrue("Category ID should be greater than 0",
                categoryIdToDelete > 0);
    }

    @When("Admin sends DELETE request to delete the category")
    public void admin_sends_delete_request_to_delete_the_category() {

        AdminCommonSteps.response =
                request.delete("/api/categories/" + categoryIdToDelete);
    }

    @Then("deleted category should not appear in category list")
    public void deleted_category_should_not_appear_in_category_list() {

        AdminCommonSteps.response =
                request.get("/api/categories");

        List<Map<String, Object>> categories =
                AdminCommonSteps.response.jsonPath().getList("$");

        boolean categoryExists = categories.stream()
                .anyMatch(cat ->
                        ((Integer) cat.get("id")) == categoryIdToDelete
                );

        assertFalse("Deleted category still exists!", categoryExists);
    }
}

