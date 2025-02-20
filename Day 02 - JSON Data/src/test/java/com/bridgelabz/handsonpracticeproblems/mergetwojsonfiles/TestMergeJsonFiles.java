package com.bridgelabz.handsonpracticeproblems.mergetwojsonfiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestMergeJsonFiles {

    @Test
    public void testMergeJsonFiles() {
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load the first JSON file
            JsonNode jsonNode1 = objectMapper.readTree(new File("src/main/resources/file1.json"));

            // Load the second JSON file
            JsonNode jsonNode2 = objectMapper.readTree(new File("src/main/resources/file2.json"));

            // Convert JsonNode to ObjectNode for merging
            ObjectNode objectNode1 = (ObjectNode) jsonNode1;
            ObjectNode objectNode2 = (ObjectNode) jsonNode2;

            // Merge the second JSON object into the first JSON object
            objectNode1.setAll(objectNode2);

            // Verify that the merged JSON object contains all fields
            assertNotNull(objectNode1.get("name"));
            assertNotNull(objectNode1.get("email"));
            assertNotNull(objectNode1.get("phone"));
            assertNotNull(objectNode1.get("address"));

            // Verify values of merged JSON object fields
            assertEquals("John Doe", objectNode1.get("name").asText());
            assertEquals("john.doe@example.com", objectNode1.get("email").asText());
            assertEquals("555-1234", objectNode1.get("phone").asText());
            assertEquals("123 Main St", objectNode1.get("address").asText());

            // Optionally print the merged JSON for visual verification
            String mergedJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode1);
            System.out.println(mergedJsonString);

        } catch (Exception e) {
            // Handle any exceptions that may occur during the test
            e.printStackTrace();
        }
    }
}
