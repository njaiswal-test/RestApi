package org.zoopla.restapi.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.zoopla.restapi.commonMethods;

public class JsonApiValidationStep {
    commonMethods commonMethodsObj=new commonMethods();
    RequestSpecification httpRequest;
    Response response;
    @Given("I am authorised user for APi {string}")
    public void i_am_authorised_user_for_a_pi(String baseURL) {
        httpRequest=commonMethodsObj.GivenBaseURL(baseURL);

    }
    @When("text parameter is passed {string}")
    public void text_parameter_is_passed(String inputText) {
        response=commonMethodsObj.GetResponseWithQueryParamter(httpRequest,inputText);
    }
    @Then("API returns response code {int}")
    public void api_returns_response_code(int statusCode) {
       commonMethodsObj.verifyApiResponseCode(statusCode,response);
    }

    @Then("API returns response body for {string} node is {string}")
    public void api_returns_response_body_for_node_is(String node, String expectedResponseBody) {
        commonMethodsObj.verifyAPiResponseBody(node,expectedResponseBody,response);
    }
    @When("text parameter is not passed")
    public void text_parameter_is_not_passed() {
        response=commonMethodsObj.GetResponseWithQueryParamter(httpRequest);
    }

    @Then("validate response output Schema")
    public void validate_response_output_schema() {

        commonMethodsObj.verifyJsonSchema(response);
        }

}
