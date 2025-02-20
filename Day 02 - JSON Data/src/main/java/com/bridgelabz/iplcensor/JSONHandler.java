package com.bridgelabz.iplcensor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONHandler {

    private IPLDataProcessor dataProcessor;

    // Constructor to initialize the IPLDataProcessor
    public JSONHandler(IPLDataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    // Method to process JSON file
    public void processJsonFile(String inputFilePath, String outputFilePath) {
        try (FileReader reader = new FileReader(inputFilePath)) {
            // Read JSON content from input file
            StringBuilder jsonBuilder = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonBuilder.append((char) i);
            }

            // Convert JSON content to a string
            String jsonString = jsonBuilder.toString();
            // Parse JSON string into a JSON array
            JSONArray jsonArray = new JSONArray(jsonString);
            // Initialize a new JSON array to store sanitized data
            JSONArray sanitizedJsonArray = new JSONArray();

            // Iterate through JSON array and censor each match
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject match = jsonArray.getJSONObject(j);
                sanitizedJsonArray.put(dataProcessor.censorJsonMatch(match));
            }

            // Write sanitized JSON array to output file with indentation
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(sanitizedJsonArray.toString(4)); // Pretty print JSON with indentation
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
