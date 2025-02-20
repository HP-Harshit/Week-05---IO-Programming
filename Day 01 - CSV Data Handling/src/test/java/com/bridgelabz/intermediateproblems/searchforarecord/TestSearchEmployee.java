package com.bridgelabz.intermediateproblems.searchforarecord;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

public class TestSearchEmployee {

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

    @AfterAll
    public static void cleanup() {
        // Delete the temporary CSV file
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testSearchEmployee() {
        // Capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Search for an employee
        Main.searchEmployee("Alice Johnson");

        // Expected output
        String expectedOutput = "Record found!\n" +
                "Department: Sales\n" +
                "Salary: 1599.99";

        // Retrieve the captured output
        String actualOutput = outContent.toString().trim().replace(System.lineSeparator(), "\n");

        // Verify the output
        Assertions.assertEquals(expectedOutput, actualOutput, "The employee details should match the expected output.");
    }

    @Test
    void testEmployeeNotFound() {
        // Capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Search for a non-existing employee
        Main.searchEmployee("Non Existing");

        // Expected output
        String expectedOutput = "Employee not found.";

        // Retrieve the captured output
        String actualOutput = outContent.toString().trim();

        // Verify the output
        Assertions.assertEquals(expectedOutput, actualOutput, "The output should indicate that the employee was not found.");
    }
}
