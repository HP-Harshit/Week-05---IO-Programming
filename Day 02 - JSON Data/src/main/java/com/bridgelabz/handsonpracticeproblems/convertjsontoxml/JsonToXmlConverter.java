package com.bridgelabz.handsonpracticeproblems.convertjsontoxml;

import org.json.JSONObject;
import org.json.XML;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        // Define a JSON string
        String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";

        // Convert JSON string to JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Convert JSONObject to XML string
        String xmlString = XML.toString(jsonObject);

        // Print the XML string
        System.out.println(xmlString);
    }
}
