package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.vf.pojo.Profile;
import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Sign_Up_Post {
    Response response;
    String consumerId;
    String consumerEmail;

    @Test
    public void ConsumerSignUp() {
        GuestAccessToken guestToken = new GuestAccessToken();
        String accessToken = guestToken.getAccessTokenTest();
        // System.out.println(accessToken);
        baseURI = ApiHeaderConfig.getBaseURI();
        Map<String, String> headers = ApiHeaderConfig.getHeaders();

        Faker faker = new Faker();

        Profile request = new Profile();

        //request.setCountry("US");
        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        //request.setLocale("en_US");
        request.setEmail(faker.internet().emailAddress());
        request.setPassword("Vfc2023$");
        //request.setIsTemporaryPassword(true);
        request.setPhone("+1617"+faker.number().numberBetween(1000000,9999999));
        //request.setHomePhone("+1617"+faker.number().numberBetween(1000000,9999999));
        //request.setBirthDate("2001-01-01");
        //request.setPostalCode(faker.address().zipCode());


        Map<String, Object> source = new HashMap<>();

        source.put("acquisitionType", "Registered");
        source.put("store", "TBL-US");
        request.setSource(source);

        Map<String, Object> subscriptions = new HashMap<>();
        subscriptions.put("newsletterConsent", true);
        //subscriptions.put("loyaltyConsent",true);
        //subscriptions.put("smsNotificationConsent",false);
        request.setSubscriptions(subscriptions);

        String json = new Gson().toJson(request);
        response = given().accept(ContentType.JSON)
                .headers(ApiHeaderConfig.getHeaders())
                .header("x-usid", "43523423432")
                //.header("Authorization", "Bearer " + accessToken)
                .contentType("application/json").headers(headers)
                .body(request)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signup");
        String jsonString = response.asString();
        JsonPath jsonPath = new JsonPath(jsonString);
        consumerId = jsonPath.getString("consumerId");
        consumerEmail = jsonPath.getString("email");

        System.out.println("Consumer ID is:" + consumerId + "\nResponse Code is: " + response.getStatusCode() + "\nConsumer Email: " + consumerEmail);

    }
}

