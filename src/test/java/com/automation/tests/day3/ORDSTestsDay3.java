package com.automation.tests.day3;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestsDay3 {

    @BeforeAll
    public static void setup () {
        baseURI = "http://54.224.118.38:1000/ords/hr";
    }
/**
 * given resource path parameter is "/regions/{id}"
 * when user makes get request
 * and region id is equals to 1
 * then assert that status code is 200
 * and assert that region name is Europe
 */

@Test
public void verifyFirstRegion(){
    given().
            pathParam("id", 1).
            when().
            get("/regions/{id}").prettyPeek().
            then().assertThat().
            statusCode(200).
            body("region_name", is("Europe")).
            body("region_id", is(1)).
            time(lessThan(5L), TimeUnit.SECONDS);


}
@Test
public void verifyEmployee() {
    Response response = given().accept(ContentType.JSON).
            when().get("/employees");
    JsonPath jsonPath = response.jsonPath();
    String namOfFirstEmployee = jsonPath.getString("items[0].first_name");
    String nameOfLastEmployee = jsonPath.getString("items[-1].first_name");//-1 to get last item in the list
    String nameOfSecondLastEmployee = jsonPath.getString("items[-2].first_name");

    System.out.println("first name of first employee: "+namOfFirstEmployee);
    System.out.println("first name of last employee: "+nameOfLastEmployee);
    System.out.println("first name of second last employee: "+nameOfSecondLastEmployee);

    Map<String, ?> firstEmployee = jsonPath.get("items[0]");//or we can put Object instead of ?
    System.out.println(firstEmployee);
}


}
