package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.google.gson.Gson;
import com.vf.pojo.Profile;
import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;

import java.util.Map;


import static com.vf.vfcApiEndPoints.consumers_eapi.qa.v2.GuestAccessToken.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Sign_In_Post {

    private static String accessToken;
    private static String cookieHeader;
    private static String usid;
    private static String codeChallenge;


    @Test
    public void testSignInAPI() {
        baseURI = ApiHeaderConfig.getBaseURI();

        String consumerEmail = "leonel.tillman@yahoo.com";

        String requestBody = "{\n" +
                "    \"type\": \"Guest\"\n" +
                "}";


        Response response = given()
                .headers(ApiHeaderConfig.getHeaders())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/token/v2/auth/token");

        response.then().statusCode(200);
        response.prettyPrint();

        accessToken = response.jsonPath().getString("accessToken");
        codeChallenge = response.jsonPath().getString("codeChallenge");
        cookieHeader = response.getCookie("vfa_TBL-US_access")+accessToken+"; "+
                response.getCookie("vfa_TBL-US_refresh") +"; " +
                response.getCookie("vfa_TBL-US_access_exp") + "; " +
                       response.getCookie("vfa_TBL-US_refresh_exp") ;
        System.out.println(cookieHeader);

        usid = response.jsonPath().getString("usid");

        Map<String, String> headers = ApiHeaderConfig.getHeaders();
        headers.put("Cookie", cookieHeader);
        headers.put("x-usid", usid);

        Profile request = new Profile();

        request.setType("Registered");
        request.setUsername(consumerEmail);
        request.setPassword("Vfc2023$");
        request.setCodeChallenge(codeChallenge);

        String json = new Gson().toJson(request);
        Response signInResponse = given().accept(ContentType.JSON)
                .headers(headers)
                .contentType("application/json")
                .body(json)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signin");
        signInResponse.prettyPrint();


    }
}
