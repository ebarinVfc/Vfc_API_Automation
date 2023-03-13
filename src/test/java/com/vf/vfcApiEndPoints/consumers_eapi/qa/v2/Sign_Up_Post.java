package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.vf.pojo.Profile;
import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Sign_Up_Post {

    private static String consumerId;
    private static String consumerEmail;

    @Test
    public void ConsumerSignUp() {
        String accessToken = GuestAccessToken.getAccessToken();
        String cookieHeader = GuestAccessToken.getCookieHeader();
        String usid = GuestAccessToken.getUsid();


        baseURI = ApiHeaderConfig.getBaseURI();
        Map<String, String> headers = ApiHeaderConfig.getHeaders();
        headers.put("Cookie", cookieHeader);
        headers.put("x-usid", usid);

        Faker faker = new Faker();

        Profile request = new Profile();
        consumerEmail= faker.internet().emailAddress();

        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        request.setEmail(consumerEmail);
        request.setPassword("Vfc2023$");
        request.setPhone("+1617" + faker.number().numberBetween(1000000, 9999999));

        Map<String, Object> source = new HashMap<>();

        source.put("acquisitionType", "Registered");
        source.put("store", "TBL-US");
        request.setSource(source);

        Map<String, Object> subscriptions = new HashMap<>();
        subscriptions.put("newsletterConsent", true);
        request.setSubscriptions(subscriptions);

        String json = new Gson().toJson(request);
        Response response = given().accept(ContentType.JSON)
                .headers(headers)
                .contentType("application/json")
                .body(json)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signup");

        String jsonString = response.asString();
        JsonPath jsonPath = new JsonPath(jsonString);
        consumerId = jsonPath.getString("consumerId");


        System.out.println("Consumer ID is:" + consumerId + "\nResponse Code is: " + response.getStatusCode() + "\nConsumer Email: " + consumerEmail);
    }

    public static String getConsumerId() {
        return consumerId;
    }

    public static String getConsumerEmail() {
        return consumerEmail;
    }
}
