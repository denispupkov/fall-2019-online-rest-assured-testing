package com.automation.review;
import io.restassured.response.Response;
import java.util.List;
import java.util.Scanner;
import static io.restassured.RestAssured.*;
public class WeatherAPP {
    static {
        baseURI = "https://www.metaweather.com/api/location";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        String woeid = getWOEID(city);
        printWeatherInfo(woeid);
    }

    public static String getWOEID(String city) {
        Response response = given().queryParam("query", city).get("/search");
        String woeid = response.jsonPath().getString("woeid");
        return woeid;
    }

    public static void printWeatherInfo(String woeid) {
        woeid = woeid.replaceAll("\\D", "");//to delete all non-digits
        Response response = get("{woeid}", woeid);
        List<String> weatherStateName = response.jsonPath().getList("consolidated_weather.weather_state_name");
        List<Double> temp = response.jsonPath().getList("consolidated_weather.the_temp");
        List<String> dates = response.jsonPath().getList("consolidated_weather.applicable_date");
        System.out.println("Here is the weather forecast for this week:");
        for (int i = 0; i < weatherStateName.size(); i++) {
            System.out.printf("Date: %s, Weather state: %s, Temperature %s\n", dates.get(i), weatherStateName.get(i), temp.get(i));
        }
    }
}