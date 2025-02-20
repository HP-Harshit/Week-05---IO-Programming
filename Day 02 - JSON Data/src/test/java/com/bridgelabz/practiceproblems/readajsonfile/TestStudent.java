package com.bridgelabz.practiceproblems.readajsonfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    @Test
    public void testStudent() {
        // Create an instance of ObjectMapper for reading JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the JSON file and convert it to an array of Student objects
            Student[] students = objectMapper.readValue(new File("src/main/resources/students.json"), Student[].class);

            // Check if the array has the correct number of Student objects
            assertEquals(3, students.length);

            // Perform assertions to check if the JSON deserialization is correct
            Student student1 = students[0];
            assertEquals("Alice Johnson", student1.getName());
            assertEquals("alice.johnson@example.com", student1.getEmail());

            Student student2 = students[1];
            assertEquals("Bob Smith", student2.getName());
            assertEquals("bob.smith@example.com", student2.getEmail());

            Student student3 = students[2];
            assertEquals("Charlie Brown", student3.getName());
            assertEquals("charlie.brown@example.com", student3.getEmail());

        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }
}
