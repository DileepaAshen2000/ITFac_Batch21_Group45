package admin.api.stepdefinitions;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertNotNull;

public class TC_ADM_API_CAT_01_Steps {

    @Then("response should contain category list")
    public void response_should_contain_category_list() {
        assertNotNull(AdminCommonSteps.response.jsonPath().getList("$"));
    }
}
