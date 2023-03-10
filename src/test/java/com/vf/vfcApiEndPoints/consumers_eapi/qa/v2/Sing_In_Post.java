package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Sing_In_Post {

    @Test
    public void testSignInAPI() {
      //  String accessToken = GuestToken.getAccessToken(); // Call getAccessToken using class name
        baseURI = ApiHeaderConfig.getBaseURI();


        // Build the request body
        String requestBody = "{\n" +
                "    \"type\": \"Registered\",\n" +
                "    \"username\": \"{{customerLogin}}\",\n" +
                "    \"password\": \"Tulips_12\",\n" +
                "    \"codeChallenge\": \"{{code_challenge}}\"\n" +
                "}";

        // Send the POST request with headers and body
        Response response = given()

                .headers(ApiHeaderConfig.getHeaders())
             //   .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signin");

        // Verify the response status code is 200
        response.then().statusCode(200);
        response.prettyPrint();

        // Perform other verifications on the response
        // ...
    }


}