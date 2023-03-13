package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GuestAccessToken {

    private static String accessToken;
    private static String cookieHeader;
    private static String usid;

    static {
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

        accessToken = response.jsonPath().getString("accessToken");
        cookieHeader = response.getCookie("vfa_TBL-US_refresh") + "; " +
                response.getCookie("vfa_VANS-CA_refresh") + "; " +
                response.getCookie("vfa_TBL-US_refresh_exp") + "; " +
                response.getCookie("vfa_VANS-CA_refresh_exp");
        usid = response.jsonPath().getString("usid");
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getCookieHeader() {
        return cookieHeader;
    }

    public static String getUsid() {
        return usid;
    }

}
