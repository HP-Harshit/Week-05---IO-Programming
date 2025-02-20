package com.bridgelabz.basicproblems.readandcountrows;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

public class TestCount {

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
        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(tempFile))) {
            // Capture the system output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            String[] nextLine;
            StringBuilder output = new StringBuilder();
            // Expected output for comparison
            String expectedOutput = "ID\tName\tAge\tMarks\n1\tJohn Doe\t20\t85\n" +
                    "2\tJane Smith\t22\t90\n" +
                    "3\tAlice Johnson\t19\t95";


            int count = -1;
            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                // Printing each record in a structured format
                System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2] + "\t" + nextLine[3]);
                // Append the captured output to the StringBuilder
                output.append(outContent.toString().trim()).append("\n");
                // Reset the ByteArrayOutputStream to capture next line's output
                outContent.reset();
                count++;
            }

            // Retrieve the captured output
            String actualOutput = output.toString().trim();

            // Verify the output
            Assertions.assertEquals(expectedOutput, actualOutput);
            Assertions.assertEquals(3, count);
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
