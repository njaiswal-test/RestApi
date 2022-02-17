Feature: To verify the JSON purgomalum API

  Background:
    Given I am authorised user for APi "https://www.purgomalum.com/service"

    Scenario: Verify the APi returns 200 response code for valid request
      When text parameter is passed "verify code is 200"
      Then API returns response code 200

  Scenario: Verify the APi response body
    When text parameter is passed "verify code is 200"
    Then API returns response body for "result" node is "verify code is 200"

    #below scenario is written to fail
  Scenario: Verify the APi response body with incorrect node
    When text parameter is passed "incorrect node"
    Then API returns response body for "text" node is "incorrect node"

    #below scenario is written to fail
  Scenario: Verify the APi responseCode is 400 for no request parameter passed
    When text parameter is not passed
    Then API returns response code 400

  Scenario: Verify the API response schema validation
    When text parameter is passed "verify code is 200"
    Then validate response output Schema