package com.bridgelabz.handsonpracticeproblems.listofjavaobjectsintoajsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonArray {

    @Test
    public void testJsonArray() {
        try {
            // Create a list of Student objects
            List<Student> students = new ArrayList<>();
            students.add(new Student(1, "Alice", "alice@example.com"));
            students.add(new Student(2, "Bob", "bob@example.com"));
            students.add(new Student(3, "Charlie", "charlie@example.com"));

            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert the list of Student objects to JSON array
            String jsonArray = objectMapper.writeValueAsString(students);

            // Expected JSON array string
            String expectedJsonArray = "[{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@example.com\"}," +
                    "{\"id\":2,\"name\":\"Bob\",\"email\":\"bob@example.com\"}," +
                    "{\"id\":3,\"name\":\"Charlie\",\"email\":\"charlie@example.com\"}]";

            // Perform assertions to check if the conversion is correct
            assertEquals(expectedJsonArray, jsonArray);

        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }
}
