package user.api.stepdefinitions;

import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class UserApiCommonAssertions {

    @Then("response status should be {int}")
    public void response_status_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), UserCommonSteps.response.getStatusCode());
    }
}
