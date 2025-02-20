package com.bridgelabz.intermediateproblems.modifyacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File filePath = new File("employee-dataset.csv");
        updateFile(filePath);
    }

    public static void updateFile(File filePath) {
        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(filePath));
             CSVWriter writer = new CSVWriter(new FileWriter("modified-employee-dataset.csv"))) {

            String[] nextLine;
            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Update the salary if the department is "IT"
                if (nextLine[2].equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(nextLine[3]);
                    salary = salary * 1.10; // Increase salary by 10%
                    nextLine[3] = String.format("%.2f", salary);
                }
                // Write the updated or unchanged record to the new file
                writer.writeNext(nextLine);
            }
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
