package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GuestAccessToken {

    public static String getAccessTokenTest() {

        String requestBody = "{\n" +
                "    \"type\": \"Guest\"\n" +
                "}";

        Response response = given()
                .baseUri(ApiHeaderConfig.getBaseURI())
                .headers(ApiHeaderConfig.getHeaders())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/token/v2/auth/token");

        response.then().statusCode(200);
        response.prettyPrint();

        String accessToken = response.jsonPath().getString("accessToken");


        return accessToken;

    }

    public static void main(String[] args) {
        String accessToken = getAccessTokenTest();
        // use the access token as needed
    }
}
