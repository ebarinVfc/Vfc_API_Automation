package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Sing_In_Post {

    private static String consumerId;
    private static String consumerEmail;

    @BeforeClass
    public static void setup() {
        Sign_Up_Post signUpPost = new Sign_Up_Post();
        signUpPost.ConsumerSignUp();
        consumerId = signUpPost.getConsumerId();
        consumerEmail = signUpPost.getConsumerEmail();
    }

    @Test
    public void testSignInAPI() {
        String accessToken = GuestAccessToken.getAccessToken();
        String cookieHeader = GuestAccessToken.getCookieHeader();
        String usid = GuestAccessToken.getUsid();

        baseURI = ApiHeaderConfig.getBaseURI();

        String requestBody = "{\n" +
                "    \"type\": \"Registered\",\n" +
                "    \"username\": \"" + consumerEmail + "\",\n" +
                "    \"password\": \"Vfc2023$\",\n" +
                "    \"codeChallenge\": \"JQLbXm0C4PD2G3zWVmNT9wCWeV6eTKHuRoYNzomSKfI\"\n" +
                "}";

        Response response = given()
                .headers(ApiHeaderConfig.getHeaders())
                .header("Cookie", cookieHeader)
                .header("x-usid", usid)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signin");

        response.then().statusCode(200);
        response.prettyPrint();
    }
}
