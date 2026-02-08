package common.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected static RequestSpecification request;

    protected static final String BASE_URL = "http://localhost:8008";

    protected static final String ADMIN_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNzcwNDY5MTYzLCJleHAiOjE3NzA0NzI3NjN9.uxUt8ZNMrDMqfYFUURtQRkMbBG1EvZ-9UNlDLB--KgA";

    protected static final String USER_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NzA0NjkyMjEsImV4cCI6MTc3MDQ3MjgyMX0.0HUd8LNcVsQdCkJcV3EZIr2xYXv3fq62zAbk2XxkcR0";

    protected void setupAdminAuth() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ADMIN_TOKEN);
    }

    protected void setupUserAuth() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + USER_TOKEN);
    }
}
