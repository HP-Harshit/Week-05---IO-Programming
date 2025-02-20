package com.bridgelabz.intermediateproblems.filterrecordsfromcsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

public class TestFilterRecords {

    private static File tempFile;

    @BeforeEach
    void setup() throws IOException {
        // Create a temporary CSV file
        tempFile = Files.createTempFile("student-dataset-test", ".csv").toFile();
        // Write data to the temporary CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            String[] header = {"ID", "Name", "Age", "Marks"};
            String[] record1 = {"1", "John Doe", "20", "85"};
            String[] record2 = {"2", "Jane Smith", "22", "90"};
            String[] record3 = {"3", "Alice Johnson", "19", "95"};
            writer.writeNext(header);
            writer.writeNext(record1);
            writer.writeNext(record2);
            writer.writeNext(record3);
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
    void testReadFile() {
        // Capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Expected output for comparison
        String expectedOutput = "ID\tName\tAge\tMarks\n1\tJohn Doe\t20\t85\n" +
                "2\tJane Smith\t22\t90\n" +
                "3\tAlice Johnson\t19\t95";

        // Call the method to filter records
        Main.filterRecords(tempFile);

        // Retrieve the captured output and normalize line separators
        String actualOutput = outContent.toString().trim().replace(System.lineSeparator(), "\n");

        // Verify the output
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
