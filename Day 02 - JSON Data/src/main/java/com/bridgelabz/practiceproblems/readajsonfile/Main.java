package com.bridgelabz.practiceproblems.readajsonfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Read the JSON file and extract specific fields (name and email)
        try {
            // Create an instance of ObjectMapper for reading JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file and convert it to an array of Student objects
            Student[] students = objectMapper.readValue(new File("src/main/resources/students.json"), Student[].class);

            // Iterate through the array of Student objects
            for (Student student : students) {
                // Print the name and email of each student
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the JSON reading process
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
