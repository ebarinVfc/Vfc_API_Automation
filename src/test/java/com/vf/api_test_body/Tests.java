package com.vf.api_test_body;

import static io.restassured.RestAssured.*;

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

import static io.restassured.RestAssured.given;

public class Tests {
    Response response;
    String consumerId;
    String consumerEmail;

    @Test
    public void postSignup_Consumer() {
        baseURI = "https://qa.xapi.vfc.com/data/v2/consumer";

        Map<String, String> headers = new HashMap<>();
        headers.put("channel", "CS");
        headers.put("brand", "VANS");
        headers.put("siteId", "VANS-US");
        headers.put("region", "NORA");
        headers.put("source", "OMS");
        headers.put("locale", "en_US");
        headers.put("client_id", "4f655085a6ec4b338786d62922667746");
        headers.put("client_secret", "e327F89fA8294bF3A5b174a64f33bA9d");
        headers.put("x-transaction-id", "A v4 style guid");

        Map<String, Object> request = new HashMap<>();
        request.put("country", "US");
        request.put("firstName", "Ayla");
        request.put("lastName", "Barin");
        request.put("email", "aylabarin@yopmail.com");
        request.put("password", "Vfc2023$");
        request.put("isTemporaryPassword", true);
        request.put("phone", "774-654-3218");
        request.put("homePhone", "617-500-1000");
        request.put("middleName", "Edith");
        request.put("title", "Ms");
        request.put("jobTitle", "BeingKid");
        Map<String, Object> source = new HashMap<>();
        source.put("store", "VANS_US}");
        source.put("acquisitionType", "Registered");
        request.put("source", source);
        Map<String, Object> subscriptions = new HashMap<>();
        subscriptions.put("newsletterConsent", false);
        request.put("subscriptions", subscriptions);

        // Send the request with the JSON body
        response = given().accept(ContentType.JSON).contentType("application/json").headers(headers)
                .body(request)
                .post("/signup");
        System.out.println(response.prettyPrint() + "\n Response Code is: " + response.getStatusCode());
    }


    //for get ("/00u6ywbxvhmGsgnq31d7")


    @Test
    public void getConsumerProfile() {
        baseURI = "https://qa.xapi.vfc.com/data/v2/consumer";
        Map<String, String> headers = new HashMap<>();
        headers.put("channel", "CS");
        headers.put("brand", "VANS");
        headers.put("siteId", "VANS-US");
        headers.put("region", "NORA");
        headers.put("source", "OMS");
        headers.put("locale", "en_US");
        headers.put("client_id", "4f655085a6ec4b338786d62922667746");
        headers.put("client_secret", "e327F89fA8294bF3A5b174a64f33bA9d");
        headers.put("x-transaction-id", "A v4 style guid");

        response = given().accept(ContentType.JSON).headers(headers)
                .get("/00u6ywbxvhmGsgnq31d7");

        System.out.println(response.body().prettyPrint());
    }


    @Test
    public void putUpdate_Consumer_ProfileI() {

        baseURI = "https://qa.xapi.vfc.com/data/v2/consumer";
        Map<String, String> headers = new HashMap<>();
        headers.put("channel", "CS");
        headers.put("brand", "VANS");
        headers.put("siteId", "VANS-US");
        headers.put("region", "NORA");
        headers.put("source", "OMS");
        headers.put("locale", "en_US");
        headers.put("client_id", "4f655085a6ec4b338786d62922667746");
        headers.put("client_secret", "e327F89fA8294bF3A5b174a64f33bA9d");
        headers.put("x-transaction-id", "A v4 style guid");

        Map<String, Object> request = new HashMap<>();
        Map<String, Object> consumerProfile = new HashMap<>();
        Map<String, Object> consumerDetails = new HashMap<>();
        consumerDetails.put("lastName", "Automation");
        consumerProfile.put("consumerDetails", consumerDetails);
        request.put("consumerProfile", consumerProfile);

        response = given().accept(ContentType.JSON).contentType("application/json").headers(headers)
                .body(request)
                .put("/00u6ywbxvhmGsgnq31d7");
        System.out.println(response.prettyPrint() + "\n Response Code is: " + response.getStatusCode());
    }

    @Test
    public void putUpdate_Consumer_ProfileII() {
        baseURI = "https://qa.xapi.vfc.com/data/v2/consumer";
        Map<String, String> headers = new HashMap<>();
        headers.put("channel", "CS");
        headers.put("brand", "VANS");
        headers.put("siteId", "VANS-US");
        headers.put("region", "NORA");
        headers.put("source", "OMS");
        headers.put("locale", "en_US");
        headers.put("client_id", "4f655085a6ec4b338786d62922667746");
        headers.put("client_secret", "e327F89fA8294bF3A5b174a64f33bA9d");
        headers.put("x-transaction-id", "A v4 style guid");

        String requestBody = "{\"consumerProfile\":{\"consumerDetails\":{\"lastName\":\"Automation\"}}}";
        response = given().accept(ContentType.JSON)
                .contentType("application/json")
                .headers(headers)
                .body(requestBody)
                .put("/00u6ywbxvhmGsgnq31d7");

        System.out.println(response.prettyPrint() + "\n Response Code is: " + response.getStatusCode());
    }

    @Test
    public void ConsumerSignUp() {
       // baseURI = "https://qa.xapi.vfc.com/data/v2/consumer";
        baseURI = ApiHeaderConfig.getBaseURI();
        Map<String, String> headers = ApiHeaderConfig.getHeaders();

        Faker faker = new Faker();

        Profile request = new Profile();

        request.setFirstName(faker.name().firstName());
        request.setLastName(faker.name().lastName());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword("Vfc2023$");
        request.setPhone(faker.phoneNumber().cellPhone());
        request.setBirthDate("2001-01-01");
        request.setPostalCode(faker.address().zipCode());


        Map<String, Object> source = new HashMap<>();
        source.put("campaignName", "signup-page");
        source.put("acquisitionType", "Registered");
        source.put("storeAssistant","1234");
        request.setSource(source);

        Map<String, Object> subscriptions = new HashMap<>();
        subscriptions.put("newsletterConsent", false);
        subscriptions.put("loyaltyConsent",true);
        subscriptions.put("smsNotificationConsent",false);
        request.setSubscriptions(subscriptions);

        String json = new Gson().toJson(request);
        response = given().accept(ContentType.JSON).contentType("application/json").headers(headers)
                .body(request)
                .post("/signup");
        String jsonString= response.asString();
        JsonPath jsonPath= new JsonPath(jsonString);
        consumerId=jsonPath.getString("consumerId");
        consumerEmail= jsonPath.getString("email");

        System.out.println("Consumer ID is:" + consumerId+"\nResponse Code is: " + response.getStatusCode()+"\nConsumer Email: "+consumerEmail);

    }
}




