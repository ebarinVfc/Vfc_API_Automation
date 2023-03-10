package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.vf.pojo.Profile;
import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class testcombine {
    private static String accessToken;
    private static String consumerId;
    private static String consumerEmail;

    @BeforeAll
    public static void setUp() {
        // Get access token
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

    }

    @Test
    public void ConsumerSignUp() {
        Faker faker = new Faker();

        Profile request = new Profile();

        request.setCountry("US");
        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        request.setLocale("en_CA");
        request.setIsTemporaryPassword(true);
        request.setEmail(faker.internet().emailAddress());
        request.setPassword("Tulips_12");
        request.setPhone(faker.phoneNumber().cellPhone());
        request.setHomePhone(faker.phoneNumber().phoneNumber());
        request.setBirthDate("2001-01-01");
        request.setPostalCode(faker.address().zipCode());

        Map<String, Object> source = new HashMap<>();
        source.put("acquisitionType", "Registered");
        source.put("store", "TBL-US");
        request.setSource(source);

        Map<String, Object> subscriptions = new HashMap<>();
        subscriptions.put("newsletterConsent", true);
        request.setSubscriptions(subscriptions);

        String json = new Gson().toJson(request);
        Response response = given().accept(ContentType.JSON)
                .baseUri(ApiHeaderConfig.getBaseURI())
                .headers(ApiHeaderConfig.getHeaders())
                .header("x-usid","43523423432")
                //.header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(request)
                .queryParam("captchaResponse", "f3b1eddd-9ca2-4e59-820b-b67196a64d04")
                .post("/api/consumers/v2/auth/signup");


        JsonPath jsonPath= response.jsonPath();
        consumerId=jsonPath.getString("consumerId");
        consumerEmail= jsonPath.getString("email");

        System.out.println("Consumer ID is:" + consumerId+"\nResponse Code is: " + response.getStatusCode()+"\nConsumer Email: "+consumerEmail);
    }
}
