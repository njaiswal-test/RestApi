package org.zoopla.restapi;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.io.File;

public class commonMethods {
  //  RequestSpecification httpRequest;
    public RequestSpecification GivenBaseURL(String baseURL)
    {
        RestAssured.baseURI = baseURL;
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest;
    }
    public Response GetResponseWithQueryParamter(RequestSpecification httpRequest1,String inputText)
    {
        Response response = httpRequest1.queryParam("text",inputText).get("/json");
        return response;
    }
    public Response GetResponseWithQueryParamter(RequestSpecification httpRequest1)
    {
        Response response = httpRequest1.get("/json");
        return response;
    }
    public void verifyApiResponseCode(int expectedCode,Response res)
    {
        System.out.println("Response Code is =>  " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),expectedCode,"the response code doesnot match the expected value");
    }
    public void verifyAPiResponseBody(String node,String expectedOutput,Response res)
    {
        String actualOutput=res.jsonPath().get(node).toString();
        System.out.println("Response Body is =>  " + res.jsonPath().get(node));
        Assert.assertEquals(actualOutput,expectedOutput,"the response body doesnot match the expected value");
    }

    public void verifyJsonSchema(Response response) {
        response.then().assertThat().
                body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\jaisw\\IdeaProjects\\RestApiAutomation\\src\\main\\resources\\JSONSchema.txt")));

    }
}
