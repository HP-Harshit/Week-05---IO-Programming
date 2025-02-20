package com.bridgelabz.practiceproblems.jsonobjectforastudent;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentJson {
    public static void main(String[] args) {
        // Create a JSON object
        JSONObject student = new JSONObject();

        // Add name and age
        student.put("name", "Alice");
        student.put("age", 20);

        // Add subjects
        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Physics");
        subjects.put("Chemistry");

        student.put("subjects", subjects);

        // Print the JSON object
        System.out.println(student.toString(3));
    }
}

