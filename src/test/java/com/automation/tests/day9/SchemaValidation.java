package com.automation.tests.day9;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SchemaValidation {

    @BeforeAll
    public static void beforeAll() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication = basic("admin", "admin");
    }


    @Test
    public void schemaValidationTest() {
        //get json schema and store it in file object
        File schema = new File("spartan.json");

        Response response0 = given().accept(ContentType.JSON).when().get("/spartans");
        List<Integer> id_collection = response0.jsonPath().getList("id");
        for (int i = 0; i < id_collection.size(); i++) {
            get("/spartans/{id}", id_collection.get(i)).
                    then().
                    body(JsonSchemaValidator.matchesJsonSchema(schema));
            System.out.println("Spartan with " + id_collection.get(i) + " id passed schema validation");
        }
    }
}