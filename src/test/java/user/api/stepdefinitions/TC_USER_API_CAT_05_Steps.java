package user.api.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TC_USER_API_CAT_05_Steps {

    @When("User sends GET request with search {string} and parent {string}")
    public void user_sends_get_request_with_search_and_parent(String search, String parent) {

        UserCommonSteps.response =
                UserCommonSteps.getRequest()
                        .queryParam("search", search)
                        .queryParam("parent", parent)
                        .get("/api/categories");
    }

    @Then("User should receive filtered category list")
    public void user_should_receive_filtered_category_list() {

        int status = UserCommonSteps.response.getStatusCode();
        String body = UserCommonSteps.response.getBody().asString();

        System.out.println("FILTER STATUS = " + status);
        System.out.println("BODY = " + body);

        assertEquals(200, status);

        // response is filtered
        assertTrue(body.contains("DEF") || body.contains("ABD"));
    }
}
