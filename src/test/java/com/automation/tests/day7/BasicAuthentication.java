package com.automation.tests.day7;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthentication {

    @Test
    public void spartanAuthentication () {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        given().auth().basic("user","user").
                when().get("/spartans").prettyPeek().
                then().statusCode(200);

    }

    @Test
    public void authorizationTest () {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        Spartan spartan = new Spartan("Araz","Male",8888444433L);
        given().auth().basic("user","user").
                body(spartan).contentType(ContentType.JSON).
                when().post("/spartans").prettyPeek().
                then().statusCode(403);

    }
    @Test
    public void authenticationTest() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

        get("/spartans").prettyPeek().then().statusCode(401);
    }

    @Test
    public void authenticationTest2 () {
        baseURI = "http://practice.cybertekschool.com";
        given().auth().basic("admin","admin").
                when().get("/basic_auth").prettyPeek().
                then().statusCode(200).contentType(ContentType.HTML);
    }
}
