package com.vf.step_defi;

import com.vf.vfcApiEndPoints.ApiHeaderConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GuestTokenStepDefinitions {

    private String endpoint;
    private Response response;
    private String accessToken;

    @Given("I have the API endpoint {string}")
    public void iHaveTheAPIEndpoint(String endpoint) {
        this.endpoint = endpoint;
        RestAssured.baseURI = endpoint;
    }

    @When("I send a POST request with request body")
    public void iSendAPOSTRequestWithRequestBody() {
        String requestBody = "{\n" +
                "    \"type\": \"Guest\"\n" +
                "}";
        response = given()
                .baseUri(ApiHeaderConfig.getBaseURI())
                .headers(ApiHeaderConfig.getHeaders())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/api/token/v2/auth/token");
    }

    @Then("the response status code should be <statusCode>")
    public void theResponseStatusCodeShouldBeStatusCode() {
        int statusCode =200;
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response body should contain an access token")
    public void theResponseBodyShouldContainAnAccessToken() {
        accessToken = response.jsonPath().getString("accessToken");
        assertNotNull(accessToken);
        System.out.println(accessToken);
    }


}


