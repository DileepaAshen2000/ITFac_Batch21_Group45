package common.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected static RequestSpecification request;

    protected static final String BASE_URL = "http://localhost:8008";

    protected static final String ADMIN_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNzcwMjc1OTQ4LCJleHAiOjE3NzAyNzk1NDh9.LYL6kHwHrkVg2Uq7derdTpAYAEStKhv7eJ0TUHWEgBM";

    protected static final String USER_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NzAyNzU5MDYsImV4cCI6MTc3MDI3OTUwNn0.6uzsyujAL6PMcnwm2L82sDmwfDzi3maKxxph3kajNjg";

    protected void setupAdminAuth() {
        RestAssured.baseURI = BASE_URL;

        String tokenFromTerminal = System.getProperty("ADMIN_TOKEN");
        String tokenToUse = (tokenFromTerminal != null && !tokenFromTerminal.isBlank())
                ? tokenFromTerminal
                : ADMIN_TOKEN;

        request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenToUse);
    }

    protected void setupUserAuth() {
        RestAssured.baseURI = BASE_URL;

        String tokenFromTerminal = System.getProperty("USER_TOKEN");
        String tokenToUse = (tokenFromTerminal != null && !tokenFromTerminal.isBlank())
                ? tokenFromTerminal
                : USER_TOKEN;

        request = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenToUse);
    }
}