package com.bridgelabz.basicproblems.writedatatoacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestWriteFile {

    private final String fileName = "employee-dataset.csv";

    @Test
    public void testWriteToFile() {
        // Run the main method to write data to the CSV file
        Main.main(null);

        // Verify that the file has been created
        File file = new File(fileName);
        Assertions.assertTrue(file.exists(), "The CSV file should be created.");

        // Verify the content of the file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] nextLine;
            StringBuilder actualOutput = new StringBuilder();

            // Expected output
            String expectedOutput = "ID,Name,Department,Salary\n" +
                    "1,John Doe,IT,2199.99\n" +
                    "2,Jane Smith,CSE,1499.99\n" +
                    "3,Alice Johnson,Sales,1599.99\n" +
                    "4,Joey Abreu,Marketing,1799.99\n" +
                    "5,Kiana Lor,HR,2599.99\n" +
                    "6,Michael Brown,IT,2200.00\n" +
                    "7,Rachel Green,Sales,1600.00\n" +
                    "8,David Lee,Marketing,1800.00\n" +
                    "9,Monica Geller,CSE,1500.00\n" +
                    "10,Phoebe Buffay,HR,2600.00";

            // Read the content of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                actualOutput.append(String.join(",", nextLine)).append("\n");
            }

            // Remove the trailing newline character
            if (actualOutput.length() > 0) {
                actualOutput.setLength(actualOutput.length() - 1);
            }

            // Assert that the actual output matches the expected output
            Assertions.assertEquals(expectedOutput, actualOutput.toString(), "The content of the CSV file should match the expected output.");
        } catch (IOException | CsvValidationException e) {
            // Handle exceptions related to reading the file
            System.out.println("Exception caught while reading the file! " + e.getMessage());
        }
    }
}
