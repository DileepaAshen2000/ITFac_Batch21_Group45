package common.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected static RequestSpecification request;

    protected static final String BASE_URL = "http://localhost:8008";

    protected static final String ADMIN_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNzcwMjI4MTI4LCJleHAiOjE3NzAyMzE3Mjh9._8sttULtDo0blGbV45z76TEnleQaLzA9KdQG6icV_Ec";

    protected static final String USER_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NzAyMjgxNzAsImV4cCI6MTc3MDIzMTc3MH0.zHERBHmwM1Hg9QIIB_o1yDAHCFjrIzIkBKKlFJQU8tM";

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
