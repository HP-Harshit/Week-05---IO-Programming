package com.bridgelabz.intermediateproblems.sortcsvrecords;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;

public class TestSortRecords {

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
            String[] record6 = {"6", "Michael Brown", "IT", "2200.00"};
            String[] record7 = {"7", "Rachel Green", "Sales", "1600.00"};
            String[] record8 = {"8", "David Lee", "Marketing", "1800.00"};
            String[] record9 = {"9", "Monica Geller", "CSE", "1500.00"};
            String[] record10 = {"10", "Phoebe Buffay", "HR", "2600.00"};
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanup() {
        // Delete the temporary CSV file after each test
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testSortAndPrintTopSalaries() {
        // Capture the system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to sort and print top salaries
        Main.sortAndPrintTopSalaries(tempFile);

        // Expected output
        String expectedOutput = "ID\tName\tDepartment\tSalary\n" +
                "10\tPhoebe Buffay\tHR\t2600.00\n" +
                "5\tKiana Lor\tHR\t2599.99\n" +
                "6\tMichael Brown\tIT\t2200.00\n" +
                "1\tJohn Doe\tIT\t2199.99\n" +
                "8\tDavid Lee\tMarketing\t1800.00";

        // Retrieve the captured output and normalize line separators
        String actualOutput = outContent.toString().trim().replace(System.lineSeparator(), "\n");

        // Verify the output
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
