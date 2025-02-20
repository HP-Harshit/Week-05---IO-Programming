package com.bridgelabz.advancedproblems.convertcsvdataintojavaobjects;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a File object for the CSV file
        File filePath = new File("student-dataset.csv");

        // Convert CSV data to list of Student objects
        List<Student> students = convertCSVToStudents(filePath);

        // Print the list of students
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static List<Student> convertCSVToStudents(File filePath) {
        List<Student> students = new ArrayList<>();

        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            boolean isFirstLine = true;

            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Convert the row to a Student object
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                int marks = Integer.parseInt(nextLine[3]);

                Student student = new Student(id, name, age, marks);
                students.add(student);
            }
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        }

        return students;
    }
}
