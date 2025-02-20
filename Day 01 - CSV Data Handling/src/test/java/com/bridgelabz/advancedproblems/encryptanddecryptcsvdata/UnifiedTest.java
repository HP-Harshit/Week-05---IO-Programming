package com.bridgelabz.advancedproblems.encryptanddecryptcsvdata;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedTest {

    private static File jsonFile;
    private static File csvFile;
    private static File outputJsonFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create temporary JSON file with test data
        jsonFile = Files.createTempFile("students-test", ".json").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
            writer.write("[");
            writer.write("{\"id\":1,\"name\":\"Kiana Lor\",\"age\":22,\"marks\":76,\"salary\":50000.0,\"email\":\"kiana@example.com\"},");
            writer.write("{\"id\":2,\"name\":\"Joshua Lonaker\",\"age\":22,\"marks\":87,\"salary\":55000.0,\"email\":\"joshua@example.com\"},");
            writer.write("{\"id\":3,\"name\":\"Dakota Blanco\",\"age\":22,\"marks\":56,\"salary\":45000.0,\"email\":\"dakota@example.com\"}");
            writer.write("]");
        }

        // Create temporary CSV file for the test
        csvFile = Files.createTempFile("students-test", ".csv").toFile();

        // Create temporary output JSON file for the test
        outputJsonFile = Files.createTempFile("students-output-test", ".json").toFile();
    }

    @AfterAll
    static void cleanup() {
        // Delete the temporary files
        if (jsonFile.exists()) {
            jsonFile.delete();
        }
        if (csvFile.exists()) {
            csvFile.delete();
        }
        if (outputJsonFile.exists()) {
            outputJsonFile.delete();
        }
    }

    @Test
    void testJsonToCsvConversion() {
        // Convert JSON to CSV with encryption
        JsonToCsvConverter.convertJsonToCsv(jsonFile.getPath(), csvFile.getPath());

        // Read the generated CSV file and verify its content
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assertEquals("ID,Name,Age,Marks,Salary,Email", header.replaceAll("\"", ""));

            String record1 = reader.readLine().replaceAll("\"", "");
            assertNotNull(record1);
            assertTrue(record1.contains("1,Kiana Lor,22,76"));

            String record2 = reader.readLine().replaceAll("\"", "");
            assertNotNull(record2);
            assertTrue(record2.contains("2,Joshua Lonaker,22,87"));

            String record3 = reader.readLine().replaceAll("\"", "");
            assertNotNull(record3);
            assertTrue(record3.contains("3,Dakota Blanco,22,56"));

        } catch (IOException e) {
            fail("Exception caught while reading the CSV file: " + e.getMessage());
        }
    }

    @Test
    void testCsvToJsonConversion() {
        // Convert JSON to CSV first to prepare the CSV file
        JsonToCsvConverter.convertJsonToCsv(jsonFile.getPath(), csvFile.getPath());

        // Convert CSV back to JSON with decryption
        CsvToJsonConverter.convertCsvToJson(csvFile.getPath(), outputJsonFile.getPath());

        // Read the generated JSON file and verify its content
        try (BufferedReader reader = new BufferedReader(new FileReader(outputJsonFile))) {
            String json = reader.readLine();

            String expectedJson = "[{\"id\":1,\"name\":\"Kiana Lor\",\"age\":22,\"marks\":76,\"salary\":50000.0,\"email\":\"kiana@example.com\"},"
                    + "{\"id\":2,\"name\":\"Joshua Lonaker\",\"age\":22,\"marks\":87,\"salary\":55000.0,\"email\":\"joshua@example.com\"},"
                    + "{\"id\":3,\"name\":\"Dakota Blanco\",\"age\":22,\"marks\":56,\"salary\":45000.0,\"email\":\"dakota@example.com\"}]";

            assertEquals(expectedJson, json);

        } catch (IOException e) {
            fail("Exception caught while reading the JSON file: " + e.getMessage());
        }
    }
}
