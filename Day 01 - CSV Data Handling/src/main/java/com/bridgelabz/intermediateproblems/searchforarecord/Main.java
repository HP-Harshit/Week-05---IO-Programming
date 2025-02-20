package com.bridgelabz.intermediateproblems.searchforarecord;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        searchEmployee("Alice Johnson");
    }

    public static void searchEmployee(String empName){
        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader("employee-dataset.csv"))) {
            String[] nextLine;
            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Printing each record in a structured format if the marks are more than 80
                if (nextLine[1].equalsIgnoreCase(empName)) {
                    System.out.println("Record found!");
                    System.out.println("Department: " + nextLine[2]);
                    System.out.println("Salary: " + nextLine[3]);
                    return;
                }
            }
            System.out.println("Employee not found.");
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handling number format exceptions
            System.out.println("Invalid number format! " + e.getMessage());
        }
    }
}
