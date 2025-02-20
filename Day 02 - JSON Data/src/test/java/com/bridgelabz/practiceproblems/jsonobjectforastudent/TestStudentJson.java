package com.bridgelabz.practiceproblems.jsonobjectforastudent;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudentJson {

    @Test
    public void testStudentJsonCreation() {
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

        // Test the JSON object
        assertEquals("Alice", student.getString("name"));
        assertEquals(20, student.getInt("age"));
        assertEquals(3, student.getJSONArray("subjects").length());
        assertEquals("Mathematics", student.getJSONArray("subjects").getString(0));
        assertEquals("Physics", student.getJSONArray("subjects").getString(1));
        assertEquals("Chemistry", student.getJSONArray("subjects").getString(2));
    }
}
