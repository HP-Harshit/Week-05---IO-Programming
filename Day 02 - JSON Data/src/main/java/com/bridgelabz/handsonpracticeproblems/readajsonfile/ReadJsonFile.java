package com.bridgelabz.handsonpracticeproblems.readajsonfile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class ReadJsonFile {

    public static void main(String[] args) {
        String jsonFile = "src/main/resources/students.json"; // Path to your JSON file

        try (FileReader reader = new FileReader(jsonFile)) {
            // Read the JSON file into a StringBuilder
            StringBuilder jsonBuilder = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonBuilder.append((char) i);
            }

            String jsonString = jsonBuilder.toString();

            // Check if the JSON data starts with { or [
            if (jsonString.trim().startsWith("{")) {
                // If it starts with {, it is a JSONObject
                JSONObject jsonObject = new JSONObject(jsonString);
                printJsonObject(jsonObject);
            } else if (jsonString.trim().startsWith("[")) {
                // If it starts with [, it is a JSONArray
                JSONArray jsonArray = new JSONArray(jsonString);
                printJsonArray(jsonArray);
            } else {
                System.out.println("Invalid JSON data");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printJsonObject(JSONObject jsonObject) {
        // Iterate over the keys of the JSONObject
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            // Check if the value is a JSONObject or JSONArray
            if (value instanceof JSONObject) {
                System.out.println(key + ": {");
                // Recursive call for nested JSONObject
                printJsonObject((JSONObject) value);
                System.out.println("}");
            } else if (value instanceof JSONArray) {
                System.out.println(key + ": [");
                // Call to print JSONArray
                printJsonArray((JSONArray) value);
                System.out.println("]");
            } else {
                System.out.println(key + ": " + value);
            }
        }
    }

    private static void printJsonArray(JSONArray jsonArray) {
        // Iterate over the elements of the JSONArray
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);

            // Check if the value is a JSONObject or another JSONArray
            if (value instanceof JSONObject) {
                System.out.print("{");
                // Recursive call for nested JSONObject
                printJsonObject((JSONObject) value);
                System.out.println("}");
            } else if (value instanceof JSONArray) {
                System.out.print("[");
                // Recursive call for nested JSONArray
                printJsonArray((JSONArray) value);
                System.out.println("]");
            } else {
                System.out.println(value);
            }
        }
    }
}

