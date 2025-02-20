package com.bridgelabz.handsonpracticeproblems.convertcsvdataintojson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvToJsonConverter {
    public static void main(String[] args) {
        // Define the CSV file path
        String csvFile = "src/main/resources/student.csv";
        String line;
        String csvSplitBy = ",";

        // Initialize a JSON array to store the JSON objects
        JSONArray jsonArray = new JSONArray();

        // Try-with-resources to automatically close the BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line
            String[] headers = br.readLine().split(csvSplitBy);

            // Trim and remove quotes from headers
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].replace("\"", "").trim();
            }

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into data fields
                String[] data = line.split(csvSplitBy);
                // Create a new JSON object
                JSONObject jsonObject = new JSONObject();

                // Add data to the JSON object with headers as keys
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], data[i].replace("\"", "").trim());
                }

                // Add JSON object to JSON array
                jsonArray.put(jsonObject);
            }

        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }

        // Print the JSON array with indentation
        System.out.println(jsonArray.toString(3));
    }
}
