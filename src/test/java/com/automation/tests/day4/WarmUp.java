package com.automation.tests.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/**
 *      Given accept type is JSON
 *      * When users sends a GET request to “/employees”
 *      * Then status code is 200
 *      * And Content type is application/json
 *      * And response time is less than 3 seconds
 */

public class WarmUp {

    @BeforeAll
    public static void setup () {
        baseURI = "http://54.224.118.38:1000/ords/hr";
    }


    @Test
    public void test () {
        given().baseUri(baseURI).contentType(ContentType.JSON).
                get("/employees").then().statusCode(200).
                time(lessThan(5L), TimeUnit.SECONDS);


    }
}
