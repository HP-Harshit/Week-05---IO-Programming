package com.bridgelabz.practiceproblems.listofjavaobjectsintoajsonarray;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
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

            // Print the JSON array
            System.out.println(jsonArray);
        } catch (Exception e) {
            // Handle any exceptions that may occur during the conversion process
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}

