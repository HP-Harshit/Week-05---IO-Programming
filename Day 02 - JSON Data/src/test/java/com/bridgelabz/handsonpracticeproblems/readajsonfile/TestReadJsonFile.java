package com.bridgelabz.handsonpracticeproblems.readajsonfile;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReadJsonFile {

    @Test
    public void testReadJsonFile() {
        // Sample JSON content
        String jsonContent = "[\n" +
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
                "    }\n" +
                "]";

        try (StringReader reader = new StringReader(jsonContent)) {
            // Read JSON content
            StringBuilder jsonBuilder = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonBuilder.append((char) i);
            }

            String jsonString = jsonBuilder.toString();

            // Assert the JSON content is read correctly
            assertTrue(jsonString.contains("\"ID\": \"1\""));
            assertTrue(jsonString.contains("\"Name\": \"Kiana Lor\""));

            // Parse JSON content
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                printJsonObject(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printJsonObject(JSONObject jsonObject) {
        jsonObject.keySet().forEach(key -> {
            Object value = jsonObject.get(key);
            System.out.println(key + ": " + value);
        });
    }
}
