package com.bridgelabz.advancedproblems.mergetwocsvfiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Paths to the CSV files
        File students1File = new File("student1.csv");
        File students2File = new File("student2.csv");
        File mergedFile = new File("merged-students.csv");

        // Merge the CSV files and write the merged data to a new file
        mergeCSVFiles(students1File, students2File, mergedFile);
    }

    public static void mergeCSVFiles(File file1, File file2, File outputFile) {
        Map<Integer, Student> studentMap = new HashMap<>();

        // Read the first CSV file and store the data in the map
        try (CSVReader reader = new CSVReader(new FileReader(file1))) {
            String[] nextLine;
            boolean isFirstLine = true;

            // Loop through each line of the first CSV file
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse the ID, Name, and Age from the current line
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                int age = Integer.parseInt(nextLine[2]);

                // Add the parsed data to the studentMap
                studentMap.put(id, new Student(id, name, age, 0, ""));
            }
        } catch (IOException | CsvValidationException e) {
            // Handle exceptions during reading of the first CSV file
            System.out.println("Exception caught while reading the first CSV file: " + e.getMessage());
        }

        // Read the second CSV file and update the map with new data
        try (CSVReader reader = new CSVReader(new FileReader(file2))) {
            String[] nextLine;
            boolean isFirstLine = true;

            // Loop through each line of the second CSV file
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse the ID, Marks, and Grade from the current line
                int id = Integer.parseInt(nextLine[0]);
                int marks = Integer.parseInt(nextLine[1]);
                String grade = nextLine[2];

                // Update the existing student object in the map with marks and grade
                if (studentMap.containsKey(id)) {
                    Student student = studentMap.get(id);
                    student.setMarks(marks);
                    student.setGrade(grade);
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Handle exceptions during reading of the second CSV file
            System.out.println("Exception caught while reading the second CSV file: " + e.getMessage());
        }

        // Write the merged data to a new CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            // Write the header row to the merged CSV file
            String[] header = {"ID", "Name", "Age", "Marks", "Grade"};
            writer.writeNext(header);

            // Loop through each student in the studentMap and write their data to the merged CSV file
            for (Student student : studentMap.values()) {
                String[] line = {String.valueOf(student.getId()), student.getName(), String.valueOf(student.getAge()),
                        String.valueOf(student.getMarks()), student.getGrade()};
                writer.writeNext(line);
            }
        } catch (IOException e) {
            // Handle exceptions during writing of the merged CSV file
            System.out.println("Exception caught while writing the merged CSV file: " + e.getMessage());
        }
    }
}
