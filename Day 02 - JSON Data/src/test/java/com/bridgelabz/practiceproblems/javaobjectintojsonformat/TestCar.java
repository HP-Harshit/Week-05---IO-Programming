package com.bridgelabz.practiceproblems.javaobjectintojsonformat;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCar {

    @Test
    void testCarToJson() {
        // Create a Car object
        Car car = new Car("Kia", "Sonet", 2023);

        // Convert the Car object to JSON format using Gson
        Gson gson = new Gson();
        String carJson = gson.toJson(car);

        // Expected JSON string
        String expectedJson = "{\"make\":\"Kia\",\"model\":\"Sonet\",\"year\":2023}";

        // Perform assertions to check if the JSON conversion is correct
        assertEquals(expectedJson, carJson);
    }
}
