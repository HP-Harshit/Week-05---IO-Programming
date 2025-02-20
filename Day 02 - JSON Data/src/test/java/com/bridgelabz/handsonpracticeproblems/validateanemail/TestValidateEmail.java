package com.bridgelabz.handsonpracticeproblems.validateanemail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class TestValidateEmail {

    @Test
    public void testValidEmail() {
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load the JSON schema
            JsonNode schemaNode = JsonLoader.fromFile(new File("src/main/resources/email-schema.json"));

            // Load valid JSON data
            String validJsonString = "{\"email\":\"john.doe@example.com\"}";
            JsonNode jsonData = objectMapper.readTree(validJsonString);

            // Create a JsonValidator instance
            JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();

            // Validate the JSON data against the schema
            ProcessingReport report = validator.validate(schemaNode, jsonData);

            // Check if validation succeeded
            assertTrue(report.isSuccess(), "Valid JSON should be valid according to the schema");
        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }

    @Test
    public void testInvalidJson() {
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load the JSON schema
            JsonNode schemaNode = JsonLoader.fromFile(new File("src/main/resources/email-schema.json"));

            // Load invalid JSON data
            String invalidJsonString = "{\"name\":\"John Doe\"}";
            JsonNode jsonData = objectMapper.readTree(invalidJsonString);

            // Create a JsonValidator instance
            JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();

            // Validate the JSON data against the schema
            ProcessingReport report = validator.validate(schemaNode, jsonData);

            // Check if validation failed
            assertFalse(report.isSuccess(), "Invalid JSON should not be valid according to the schema");
        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }
}
