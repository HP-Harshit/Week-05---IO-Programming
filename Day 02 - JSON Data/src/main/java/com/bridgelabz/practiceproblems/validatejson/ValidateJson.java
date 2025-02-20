package com.bridgelabz.practiceproblems.validatejson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import java.io.File;

public class ValidateJson {
    public static void main(String[] args) {
        try {
            // Create an instance of ObjectMapper for reading JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Load the JSON schema
            JsonNode schemaNode = JsonLoader.fromFile(new File("src/main/resources/email-schema.json"));

            // Load the JSON data to be validated
            String jsonString = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";
            JsonNode jsonData = objectMapper.readTree(jsonString);

            // Create a JsonValidator instance
            JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();

            // Validate the JSON data against the schema
            ProcessingReport report = validator.validate(schemaNode, jsonData);

            // Print the validation results
            if (report.isSuccess()) {
                System.out.println("JSON is valid against the schema.");
            } else {
                System.out.println("JSON is invalid. Validation errors:");
                System.out.println(report);
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the validation process
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
