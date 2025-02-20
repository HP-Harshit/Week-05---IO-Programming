package com.bridgelabz.advancedproblems.detectduplicatesinacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Path to the CSV file
        File csvFile = new File("duplicate-student-dataset.csv");

        // Detect and print duplicate records
        detectDuplicates(csvFile);
    }

    public static void detectDuplicates(File file) {
        Map<Integer, Student> studentMap = new HashMap<>();
        Set<Student> duplicates = new HashSet<>();

        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] nextLine;
            boolean isFirstLine = true;

            // Loop through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse the ID, Name, Age, Marks, and Grade from the current line
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                int marks = Integer.parseInt(nextLine[3]);
                String grade = nextLine[4];

                // Create a Student object
                Student student = new Student(id, name, age, marks, grade);

                // Check for duplicates
                if (studentMap.containsKey(id)) {
                    duplicates.add(student);
                } else {
                    studentMap.put(id, student);
                }
            }

            // Print all duplicate records
            if (!duplicates.isEmpty()) {
                System.out.println("Duplicate records found:");
                for (Student duplicate : duplicates) {
                    System.out.println(duplicate);
                }
            } else {
                System.out.println("No duplicate records found.");
            }

        } catch (IOException | CsvValidationException e) {
            // Handle exceptions and print the error message
            System.out.println("Exception caught while reading the CSV file: " + e.getMessage());
        }
    }
}
