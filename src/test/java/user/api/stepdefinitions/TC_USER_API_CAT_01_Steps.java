package user.api.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class TC_USER_API_CAT_01_Steps {

    @When("User sends POST request to create category")
    public void user_sends_post_request_to_create_category() {

        String payload = """
                {
                  "name": "ABC15",
                  "parentId": null
                }
                """;

        UserCommonSteps.response =
                UserCommonSteps.getRequest()
                        .body(payload)
                        .post("/api/categories");
    }

    @Then("User should receive forbidden response")
    public void user_should_receive_forbidden_response() {

        int status = UserCommonSteps.response.getStatusCode();

        System.out.println("STATUS = " + status);
        System.out.println("BODY = " + UserCommonSteps.response.getBody().asString());

        assertTrue(
                "Expected 401 or 403 but got " + status,
                status == 401 || status == 403
        );
    }
}
