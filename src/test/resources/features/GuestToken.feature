@token
Feature: Get Guest Token
  As a user, I want to get a guest token


  Scenario: Successfully get a guest token
    Given I have the API endpoint "/api/token/v2/auth/token"
    When I send a POST request with request body
    Then the response status code should be <statusCode>
    And the response body should contain an access token

