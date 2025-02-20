package com.bridgelabz.advancedproblems.detectduplicatesinacsvfile;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDetectDuplicates {

    private static File csvFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create a temporary CSV file with test data
        csvFile = Files.createTempFile("students-test", ".csv").toFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] header = {"ID", "Name", "Age", "Marks", "Grade"};
            String[] record1 = {"1", "Kiana Lor", "22", "76", "B"};
            String[] record2 = {"2", "Joshua Lonaker", "22", "87", "A"};
            String[] record3 = {"3", "Dakota Blanco", "22", "56", "D"};
            String[] record4 = {"4", "Natasha Yarusso", "20", "98", "A"};
            String[] record5 = {"5", "Brooke Cazares", "21", "56", "D"};
            String[] record6 = {"6", "Rochelle Johnson", "21", "73", "C"};
            String[] record7 = {"7", "Joey Abreu", "22", "75", "B"};
            String[] record8 = {"8", "Preston Suarez", "22", "87", "A"};
            String[] record9 = {"9", "Lee Dong", "24", "68", "C"};
            String[] record10 = {"10", "Maa'iz al-Dia", "22", "96", "A"};
            String[] record11 = {"11", "Maja Nicholson", "23", "92", "A"};
            String[] record12 = {"3", "Dakota Blanco", "22", "56", "D"};
            String[] record13 = {"7", "Joey Abreu", "22", "75", "B"};
            String[] record14 = {"8", "Preston Suarez", "22", "87", "A"};
            writer.writeNext(header);
            writer.writeNext(record1);
            writer.writeNext(record2);
            writer.writeNext(record3);
            writer.writeNext(record4);
            writer.writeNext(record5);
            writer.writeNext(record6);
            writer.writeNext(record7);
            writer.writeNext(record8);
            writer.writeNext(record9);
            writer.writeNext(record10);
            writer.writeNext(record11);
            writer.writeNext(record12);
            writer.writeNext(record13);
            writer.writeNext(record14);
        }
    }

    @AfterAll
    static void cleanup() {
        // Delete the temporary CSV file after all tests are done
        if (csvFile.exists()) {
            csvFile.delete();
        }
    }

    @Test
    void testDetectDuplicates() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the detectDuplicates method to find duplicates
        Main.detectDuplicates(csvFile);

        String output = outContent.toString().trim().replaceAll(System.lineSeparator(), "\n");

        // Expected output for duplicate records
        String expectedOutput = "Duplicate records found:\n" +
                "Student{id=8, name='Preston Suarez', age=22, marks=87, grade='A'}\n" +
                "Student{id=3, name='Dakota Blanco', age=22, marks=56, grade='D'}\n" +
                "Student{id=7, name='Joey Abreu', age=22, marks=75, grade='B'}";

        // Verify the output
        assertEquals(expectedOutput, output, "The duplicate records should match the expected output.");
    }
}