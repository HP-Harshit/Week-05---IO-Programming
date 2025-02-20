package com.bridgelabz.intermediateproblems.filterrecordsfromcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File filePath = new File("student-dataset.csv");
        filterRecords(filePath);
    }

    public static void filterRecords(File filePath) {
        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            boolean isFirstLine = true;
            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2] + "\t" + nextLine[3]);
                    continue;
                }
                // Printing each record in a structured format if the marks are more than 80
                if (Integer.parseInt(nextLine[3]) > 80) {
                    System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2] + "\t" + nextLine[3]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handling number format exceptions
            System.out.println("Invalid number format! " + e.getMessage());
        }
    }
}
