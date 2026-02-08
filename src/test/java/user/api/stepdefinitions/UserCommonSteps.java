package user.api.stepdefinitions;

import common.api.BaseApi;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserCommonSteps extends BaseApi {

    private static RequestSpecification userRequest;
    public static Response response;

    @Given("User is authenticated")
    public void user_is_authenticated() {
        setupUserAuth();
        userRequest = request;
    }

    public static RequestSpecification getRequest() {
        return userRequest;
    }
}
