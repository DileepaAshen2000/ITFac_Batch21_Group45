package user.api.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class TC_USER_API_CAT_03_Steps {

    @When("User sends DELETE request to delete category with id {int}")
    public void user_sends_delete_request(Integer id) {

        UserCommonSteps.response =
                UserCommonSteps.getRequest()
                        .delete("/api/categories/" + id);
    }

    @Then("User should receive forbidden delete response")
    public void user_should_receive_forbidden_delete_response() {

        int status = UserCommonSteps.response.getStatusCode();

        System.out.println("DELETE STATUS = " + status);
        System.out.println("BODY = " + UserCommonSteps.response.getBody().asString());

        assertEquals(403, status);
    }
}
