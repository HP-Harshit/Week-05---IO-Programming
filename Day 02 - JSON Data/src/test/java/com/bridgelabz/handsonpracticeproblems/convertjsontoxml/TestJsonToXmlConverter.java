package com.bridgelabz.handsonpracticeproblems.convertjsontoxml;

import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonToXmlConverter {

    @Test
    public void testJsonToXmlConversion() {
        // Define a JSON string
        String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";

        // Expected XML output
        String expectedXmlString = "<name>John Doe</name><age>30</age><email>john.doe@example.com</email>";

        // Convert JSON string to JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Convert JSONObject to XML string
        String xmlString = XML.toString(jsonObject);

        // Assert that the XML string matches the expected output
        assertEquals(expectedXmlString, xmlString);
    }
}
