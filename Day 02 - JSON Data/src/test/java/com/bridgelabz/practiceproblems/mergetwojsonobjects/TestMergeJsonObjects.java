package com.bridgelabz.practiceproblems.mergetwojsonobjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMergeJsonObjects {

    @Test
    public void testMergeJsonObjects() {
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Define JSON strings
            String jsonString1 = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";
            String jsonString2 = "{\"phone\":\"555-1234\",\"address\":\"123 Main St\"}";

            // Convert JSON strings to ObjectNode
            ObjectNode jsonNode1 = (ObjectNode) objectMapper.readTree(jsonString1);
            ObjectNode jsonNode2 = (ObjectNode) objectMapper.readTree(jsonString2);

            // Merge jsonNode2 into jsonNode1
            jsonNode1.setAll(jsonNode2);

            // Convert merged ObjectNode to JSON string
            String mergedJsonString = objectMapper.writeValueAsString(jsonNode1);

            // Expected JSON string
            String expectedJsonString = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"555-1234\",\"address\":\"123 Main St\"}";

            // Perform assertions to check if the merging is correct
            assertEquals(expectedJsonString, mergedJsonString);

        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }
}
