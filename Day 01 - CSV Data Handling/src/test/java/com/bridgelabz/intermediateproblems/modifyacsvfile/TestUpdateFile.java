package com.bridgelabz.intermediateproblems.modifyacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class TestUpdateFile {

    private static File tempFile;

    @BeforeEach
    void setup() throws IOException {
        // Create a temporary CSV file
        tempFile = Files.createTempFile("employee-dataset-test", ".csv").toFile();
        // Write data to the temporary CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            String[] header = {"ID", "Name", "Department", "Salary"};
            String[] record1 = {"1", "John Doe", "IT", "2199.99"};
            String[] record2 = {"2", "Jane Smith", "CSE", "1499.99"};
            String[] record3 = {"3", "Alice Johnson", "Sales", "1599.99"};
            String[] record4 = {"4", "Joey Abreu", "Marketing", "1799.99"};
            String[] record5 = {"5", "Kiana Lor", "HR", "2599.99"};
            writer.writeNext(header);
            writer.writeNext(record1);
            writer.writeNext(record2);
            writer.writeNext(record3);
            writer.writeNext(record4);
            writer.writeNext(record5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanup() {
        // Delete the temporary CSV file after each test
        File modifiedFile = new File("modified-employee-dataset.csv");
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        if (modifiedFile.exists()) {
            modifiedFile.delete();
        }
    }

    @Test
    void testUpdateFile() {
        // Call the method to update the file
        Main.updateFile(tempFile);

        // Verify the content of the modified file
        try (CSVReader reader = new CSVReader(new FileReader("modified-employee-dataset.csv"))) {
            String[] nextLine;
            StringBuilder actualOutput = new StringBuilder();

            // Expected output
            String expectedOutput = "ID,Name,Department,Salary\n" +
                    "1,John Doe,IT,2419.99\n" +
                    "2,Jane Smith,CSE,1499.99\n" +
                    "3,Alice Johnson,Sales,1599.99\n" +
                    "4,Joey Abreu,Marketing,1799.99\n" +
                    "5,Kiana Lor,HR,2599.99";

            // Read the content of the modified CSV file
            while ((nextLine = reader.readNext()) != null) {
                actualOutput.append(String.join(",", nextLine)).append("\n");
            }

            // Remove the trailing newline character
            if (actualOutput.length() > 0) {
                actualOutput.setLength(actualOutput.length() - 1);
            }

            // Verify the output
            Assertions.assertEquals(expectedOutput, actualOutput.toString(), "The content of the modified CSV file should match the expected output.");
        } catch (IOException | CsvValidationException e) {
            // Handle exceptions related to reading the file
            System.out.println("Exception caught while reading the file! " + e.getMessage());
        }
    }
}
