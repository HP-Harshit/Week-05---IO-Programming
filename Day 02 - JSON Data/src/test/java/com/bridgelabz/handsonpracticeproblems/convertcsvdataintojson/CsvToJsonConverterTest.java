package com.bridgelabz.handsonpracticeproblems.convertcsvdataintojson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvToJsonConverterTest {

    @Test
    public void testCsvToJsonConversion() {
        // Define the path to the CSV file
        String csvFile = "src/main/resources/student.csv"; // Use a test CSV file
        // Define the expected JSON output
        String expectedJsonOutput = "[\n" +
                "    {\n" +
                "        \"ID\": \"1\",\n" +
                "        \"Marks\": \"76\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Kiana Lor\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"2\",\n" +
                "        \"Marks\": \"87\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Joshua Lonaker\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"3\",\n" +
                "        \"Marks\": \"56\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Dakota Blanco\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"4\",\n" +
                "        \"Marks\": \"98\",\n" +
                "        \"Age\": \"20\",\n" +
                "        \"Name\": \"Natasha Yarusso\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"5\",\n" +
                "        \"Marks\": \"56\",\n" +
                "        \"Age\": \"21\",\n" +
                "        \"Name\": \"Brooke Cazares\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"6\",\n" +
                "        \"Marks\": \"73\",\n" +
                "        \"Age\": \"21\",\n" +
                "        \"Name\": \"Rochelle Johnson\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"7\",\n" +
                "        \"Marks\": \"75\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Joey Abreu\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"8\",\n" +
                "        \"Marks\": \"87\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Preston Suarez\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"9\",\n" +
                "        \"Marks\": \"68\",\n" +
                "        \"Age\": \"24\",\n" +
                "        \"Name\": \"Lee Dong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"10\",\n" +
                "        \"Marks\": \"96\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Maa'iz al-Dia\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"11\",\n" +
                "        \"Marks\": \"92\",\n" +
                "        \"Age\": \"23\",\n" +
                "        \"Name\": \"Maja Nicholson\"\n" +
                "    }\n" +
                "]";

        // Initialize a JSON array to store the converted JSON objects
        JSONArray jsonArray = new JSONArray();

        // Try-with-resources to automatically close the BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String csvSplitBy = ",";

            // Read the header line from the CSV file
            String[] headers = br.readLine().split(csvSplitBy);

            // Trim and remove quotes from headers
            for (int i = 0; headers.length > i; i++) {
                headers[i] = headers[i].replace("\"", "").trim();
            }

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into data fields
                String[] data = line.split(csvSplitBy);
                // Create a new JSON object
                JSONObject jsonObject = new JSONObject();

                // Add data to the JSON object with headers as keys
                for (int i = 0; headers.length > i; i++) {
                    jsonObject.put(headers[i], data[i].replace("\"", "").trim());
                }

                // Add JSON object to JSON array
                jsonArray.put(jsonObject);
            }

        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }

        // Assert that the JSON array matches the expected JSON output
        assertEquals(expectedJsonOutput.trim(), jsonArray.toString(4).trim());
    }
}
