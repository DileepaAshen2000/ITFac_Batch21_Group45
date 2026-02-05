package user.api.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class TC_USER_API_CAT_02_Steps {

    @When("User sends PUT request to update category with id {int}")
    public void user_sends_put_request_to_update_category(Integer id) {

        String payload = """
                {
                  "name": "UpdatedName",
                  "parentId": 145
                }
                """;

        UserCommonSteps.response =
                UserCommonSteps.getRequest()
                        .body(payload)
                        .put("/api/categories/" + id);
    }

    @Then("User should receive access denied response")
    public void user_should_receive_access_denied_response() {

        int status = UserCommonSteps.response.getStatusCode();

        System.out.println("STATUS = " + status);
        System.out.println("BODY = " + UserCommonSteps.response.getBody().asString());

        assertEquals(403, status);
    }
}
