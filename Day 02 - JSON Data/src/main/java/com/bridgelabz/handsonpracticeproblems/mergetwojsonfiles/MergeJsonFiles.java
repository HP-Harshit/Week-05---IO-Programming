package com.bridgelabz.handsonpracticeproblems.mergetwojsonfiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeJsonFiles {
    public static void main(String[] args) {
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

            // Convert the merged ObjectNode back to a JSON string
            String mergedJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode1);

            // Print the merged JSON string
            System.out.println(mergedJsonString);
        } catch (Exception e) {
            // Handle any exceptions that may occur during the merging process
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
