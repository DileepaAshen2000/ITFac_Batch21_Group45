package common.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRequest {

    public static Response get(String token, String path) {
        return RestAssured.given()
                .baseUri(ApiConfig.baseUrl())
                .header("Authorization", token != null ? "Bearer " + token : null)
                .accept(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract().response();
    }

    public static Response get(String token, String path, Map<String, Object> queryParams) {
        return RestAssured.given()
                .baseUri(ApiConfig.baseUrl())
                .header("Authorization", token != null ? "Bearer " + token : null)
                .queryParams(queryParams)
                .accept(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .extract().response();
    }

    public static Response post(String token, String path, Object body) {
        return RestAssured.given()
                .baseUri(ApiConfig.baseUrl())
                .header("Authorization", token != null ? "Bearer " + token : null)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract().response();
    }

    public static Response put(String token, String path, Object body) {
        return RestAssured.given()
                .baseUri(ApiConfig.baseUrl())
                .header("Authorization", token != null ? "Bearer " + token : null)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .put(path)
                .then()
                .extract().response();
    }

    public static Response delete(String token, String path) {
        return RestAssured.given()
                .baseUri(ApiConfig.baseUrl())
                .header("Authorization", token != null ? "Bearer " + token : null)
                .when()
                .delete(path)
                .then()
                .extract().response();
    }
}
