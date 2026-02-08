package admin.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class AdminCommonSteps extends BaseApi {

    public static Response response;
    public static Object token;

    @Given("Admin is authenticated")
    public void admin_is_authenticated() {
        setupAdminAuth();
    }

    @When("Admin sends GET request to fetch categories")
    public void admin_sends_get_request_to_fetch_categories() {
        response = request.get("/api/categories");
    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer statusCode) {
        System.out.println("STATUS = " + response.getStatusCode());
        System.out.println("BODY = " + response.getBody().asString());

        assertEquals(statusCode.intValue(), response.getStatusCode());
    }
}
