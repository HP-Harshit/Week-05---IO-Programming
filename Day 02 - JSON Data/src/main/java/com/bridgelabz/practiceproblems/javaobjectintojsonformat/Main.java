package com.bridgelabz.practiceproblems.javaobjectintojsonformat;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // Create a Car object
        Car car = new Car("Kia", "Sonet", 2023);

        // Convert the Car object to JSON format
        Gson gson = new Gson();
        String carJson = gson.toJson(car);

        // Print the JSON representation of the Car object
        System.out.println(carJson);
    }
}
