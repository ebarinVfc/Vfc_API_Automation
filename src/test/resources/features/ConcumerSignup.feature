@signup
Feature: Consumer Signup

  As a user, I want to sign up and create a new consumer on TBL QA Environment

  Background:
    Given I have a guest token

  Scenario: Successful Signup
    When I sign up with a new consumer email and password
    Then I receive a successful signup response with the consumer ID and email

