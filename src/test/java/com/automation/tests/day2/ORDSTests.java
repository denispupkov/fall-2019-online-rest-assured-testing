package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSTests {
    String BASE_URL = "http://3.90.112.152:1000/ords/hr";

    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees() {
        Response response = given().
                baseUri(BASE_URL).
                when().
                get("/employees").prettyPeek();

    }

    @Test
    @DisplayName("Get employee under specific ID")
    public void getOneEmployee() {
        //in URL we can specify path and query parameters
        //path parameters are used to retrieve specific resource: for example 1 employee not all of them
        //{id} - path variable, that will be replace with a value after comma
        //after when() we specify HTTP request type/method/verb
        //The path parameters. E.g. if path is "/book/{hotelId}/{roomNumber}" you can do <code>get("/book/{hotelName}/{roomNumber}", "Hotels R Us", 22);</code>.
        Response response = given().baseUri(BASE_URL).when().get("/employees/{id}", 100).prettyPeek();
        //how we verify response? - use assertions

        response.then().statusCode(200);//to verify that status is 200
    }

    @Test
    @DisplayName("Get Countries")
    public void getAllCountries () {

        given().baseUri(BASE_URL).
                when().get("/countries").
                prettyPeek().then().
                statusCode(200).statusLine("HTTP/1.1 200 OK");

}

}
