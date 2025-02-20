package com.bridgelabz.advancedproblems.readlargefile;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReadLargeCSVFile {

    private static File largeCSVFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create a temporary CSV file with large test data
        largeCSVFile = Files.createTempFile("large-dataset-test", ".csv").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(largeCSVFile))) {
            writer.write("ID,Name,Age,Marks,Grade\n"); // Write header

            // Write 999 lines of test data
            Random random = new Random();
            for (int i = 1; i <= 999; i++) {
                int age = random.nextInt(10) + 20;
                int marks = random.nextInt(100) + 1;
                char grade = (char) ('A' + random.nextInt(5));
                writer.write(i + ",Student" + i + "," + age + "," + marks + "," + grade + "\n");
            }
        }
    }

    @AfterAll
    static void cleanup() {
        // Delete the temporary CSV file after all tests are done
        if (largeCSVFile.exists()) {
            largeCSVFile.delete();
        }
    }

    @Test
    void testProcessLargeCSVFile() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Process the large CSV file in chunks of 100 lines
        Main.processLargeCSVFile(largeCSVFile, 100);

        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        // Verify the content of the first chunk's output
        String firstChunk = lines[0];
        assertEquals("Processed 100 lines. Total records processed: 100", firstChunk, "First chunk output should match.");

        // Verify the content of the last chunk's output
        String lastChunk = lines[lines.length - 1];
        assertEquals("Processed 99 lines. Total records processed: 999", lastChunk, "Last chunk output should match.");
    }
}
