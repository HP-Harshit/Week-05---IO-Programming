package com.bridgelabz.advancedproblems.mergetwocsvfiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMergeCSVFiles {

    private static File students1File;
    private static File students2File;
    private static File mergedFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create temporary CSV files with test data
        students1File = Files.createTempFile("students1-test", ".csv").toFile();
        students2File = Files.createTempFile("students2-test", ".csv").toFile();
        mergedFile = Files.createTempFile("merged-students-test", ".csv").toFile();

        try (CSVWriter writer = new CSVWriter(new FileWriter(students1File))) {
            String[] header = {"ID", "Name", "Age"};
            String[] record1 = {"1", "Kiana Lor", "22"};
            String[] record2 = {"2", "Joshua Lonaker", "22"};
            String[] record3 = {"3", "Dakota Blanco", "22"};
            String[] record4 = {"4", "Natasha Yarusso", "20"};
            String[] record5 = {"5", "Brooke Cazares", "21"};
            String[] record6 = {"6", "Rochelle Johnson", "21"};
            String[] record7 = {"7", "Joey Abreu", "22"};
            String[] record8 = {"8", "Preston Suarez", "22"};
            String[] record9 = {"9", "Lee Dong", "24"};
            String[] record10 = {"10", "Maa'iz al-Dia", "22"};
            String[] record11 = {"11", "Maja Nicholson", "23"};
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
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(students2File))) {
            String[] header = {"ID", "Marks", "Grade"};
            String[] record1 = {"1", "76", "B"};
            String[] record2 = {"2", "87", "A"};
            String[] record3 = {"3", "56", "D"};
            String[] record4 = {"4", "98", "A"};
            String[] record5 = {"5", "56", "D"};
            String[] record6 = {"6", "73", "C"};
            String[] record7 = {"7", "75", "B"};
            String[] record8 = {"8", "87", "A"};
            String[] record9 = {"9", "68", "C"};
            String[] record10 = {"10", "96", "A"};
            String[] record11 = {"11", "92", "A"};
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
        }
    }

    @AfterAll
    static void cleanup() {
        // Delete the temporary CSV files after all tests are done
        if (students1File.exists()) {
            students1File.delete();
        }
        if (students2File.exists()) {
            students2File.delete();
        }
        if (mergedFile.exists()) {
            mergedFile.delete();
        }
    }

    @Test
    void testMergeCSVFiles() {
        // Call the mergeCSVFiles method to merge the CSV data
        Main.mergeCSVFiles(students1File, students2File, mergedFile);

        // Read the merged CSV file and verify its content
        try (CSVReader reader = new CSVReader(new FileReader(mergedFile))) {
            List<String[]> records = reader.readAll();
            assertEquals(12, records.size(), "The merged file should contain 12 rows including the header.");

            // Verify the header row
            String[] header = records.get(0);
            assertEquals("ID", header[0], "Header should match");
            assertEquals("Name", header[1], "Header should match");
            assertEquals("Age", header[2], "Header should match");
            assertEquals("Marks", header[3], "Header should match");
            assertEquals("Grade", header[4], "Header should match");

            // Verify the first data row
            String[] record1 = records.get(1);
            assertEquals("1", record1[0], "ID should match");
            assertEquals("Kiana Lor", record1[1], "Name should match");
            assertEquals("22", record1[2], "Age should match");
            assertEquals("76", record1[3], "Marks should match");
            assertEquals("B", record1[4], "Grade should match");

            // Verify the last data row
            String[] record11 = records.get(11);
            assertEquals("11", record11[0], "ID should match");
            assertEquals("Maja Nicholson", record11[1], "Name should match");
            assertEquals("23", record11[2], "Age should match");
            assertEquals("92", record11[3], "Marks should match");
            assertEquals("A", record11[4], "Grade should match");
        } catch (IOException | CsvException e) {
            System.out.println("Exception caught while reading the merged CSV file: " + e.getMessage());
        }
    }
}
