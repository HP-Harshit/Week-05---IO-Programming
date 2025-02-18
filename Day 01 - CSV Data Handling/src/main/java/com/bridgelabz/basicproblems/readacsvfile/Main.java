package com.bridgelabz.basicproblems.readacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader("student-dataset.csv"))) {
            String[] nextLine;
            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Printing each record in a structured format
                System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2] + "\t" + nextLine[3]);
            }
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
