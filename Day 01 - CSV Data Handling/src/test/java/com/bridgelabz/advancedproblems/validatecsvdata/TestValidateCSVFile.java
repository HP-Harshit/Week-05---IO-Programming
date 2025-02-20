package com.bridgelabz.advancedproblems.validatecsvdata;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class TestValidateCSVFile {

    private static File tempFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create a temporary CSV file with test data
        tempFile = Files.createTempFile("employee-dataset-test", ".csv").toFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            String[] header = {"ID", "Name", "Email", "Phone", "Department", "Salary"};
            String[] record1 = {"1", "John Doe", "john.doe@example.com", "9876543210", "IT", "2199.99"};
            String[] record2 = {"2", "Jane Smith", "jane.smith@invalid", "1234567890", "CSE", "1499.99"};
            String[] record3 = {"3", "Alice Johnson", "alice.johnson@example.com", "9876543212", "Sales", "1599.99"};
            String[] record4 = {"4", "Joey Abreu", "joey.abreu@example.com", "1234567890", "Marketing", "1799.99"};
            String[] record5 = {"5", "Kiana Lor", "kiana.lor@example.com", "9876543214", "HR", "2599.99"};
            String[] record6 = {"6", "Michael Brown", "michael.brown@invalid.com", "+11234567890", "IT", "2200.00"};
            String[] record7 = {"7", "Rachel Green", "rachel.green@example.com", "9876543216", "Sales", "1600.00"};
            String[] record8 = {"8", "David Lee", "david.lee@invalid", "2345678901", "Marketing", "1800.00"};
            String[] record9 = {"9", "Monica Geller", "monica.geller@example.com", "9012345678", "CSE", "1500.00"};
            String[] record10 = {"10", "Phoebe Buffay", "phoebe.buffay@example.com", "0123456789", "HR", "2600.00"};
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
        }
    }

    @AfterAll
    static void cleanup() {
        // Delete the test CSV file after all tests are done
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testValidateCSVData() {
        // Create a ByteArrayOutputStream to capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the validateCSVData method to validate the data
        Main.validateCSVData(tempFile);

        // Expected output for invalid rows
        String expectedOutput = "Invalid data found in line 2: 2,Jane Smith,jane.smith@invalid,1234567890,CSE,1499.99\n" +
                "Invalid Email: jane.smith@invalid\n" +
                "Invalid Phone Number: 1234567890\n" +
                "Invalid data found in line 4: 4,Joey Abreu,joey.abreu@example.com,1234567890,Marketing,1799.99\n" +
                "Invalid Phone Number: 1234567890\n" +
                "Invalid data found in line 6: 6,Michael Brown,michael.brown@invalid.com,+11234567890,IT,2200.00\n" +
                "Invalid Phone Number: +11234567890\n" +
                "Invalid data found in line 8: 8,David Lee,david.lee@invalid,2345678901,Marketing,1800.00\n" +
                "Invalid Email: david.lee@invalid\n" +
                "Invalid Phone Number: 2345678901\n" +
                "Invalid data found in line 10: 10,Phoebe Buffay,phoebe.buffay@example.com,0123456789,HR,2600.00\n" +
                "Invalid Phone Number: 0123456789";

        // Retrieve the captured output
        String actualOutput = outContent.toString().trim().replace(System.lineSeparator(), "\n");

        // Verify the output
        Assertions.assertEquals(expectedOutput, actualOutput, "The invalid data should be correctly identified and printed.");
    }
}
