package com.bridgelabz.advancedproblems.generateacsvreportfromdatabase;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGenerateCSVReport {

    private static Connection connection;
    private static File csvFile;

    @BeforeAll
    static void setup() throws Exception {
        // Setup in-memory H2 database
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "user", "User@123");

        // Create employees table and insert test data
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE employees (EmployeeID INT PRIMARY KEY, Name VARCHAR(100), Department VARCHAR(50), Salary DECIMAL(10, 2));");
            statement.execute("INSERT INTO employees (EmployeeID, Name, Department, Salary) VALUES (1, 'John Doe', 'IT', 50000), (2, 'Jane Smith', 'HR', 55000), (3, 'Alice Johnson', 'Sales', 60000), (4, 'Bob Brown', 'Marketing', 45000), (5, 'Emily Davis', 'IT', 52000);");
        }

        // Create a temporary CSV file for the test
        csvFile = Files.createTempFile("employee-report-test", ".csv").toFile();
    }

    @AfterAll
    static void cleanup() throws Exception {
        // Close the database connection and delete the temporary CSV file
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        if (csvFile.exists()) {
            csvFile.delete();
        }
    }

    @Test
    void testGenerateCSVReport() throws Exception {
        // Call the method to generate the CSV report
        Main.generateCSVReport("jdbc:h2:mem:testdb", "sa", "", csvFile.getPath());

        // Read the generated CSV file and verify its content
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] header = reader.readNext();
            assertEquals("Employee ID", header[0]);
            assertEquals("Name", header[1]);
            assertEquals("Department", header[2]);
            assertEquals("Salary", header[3]);

            String[] record1 = reader.readNext();
            assertEquals("1", record1[0]);
            assertEquals("John Doe", record1[1]);
            assertEquals("IT", record1[2]);
            assertEquals("50000.00", record1[3]);

            String[] record2 = reader.readNext();
            assertEquals("2", record2[0]);
            assertEquals("Jane Smith", record2[1]);
            assertEquals("HR", record2[2]);
            assertEquals("55000.00", record2[3]);

            String[] record3 = reader.readNext();
            assertEquals("3", record3[0]);
            assertEquals("Alice Johnson", record3[1]);
            assertEquals("Sales", record3[2]);
            assertEquals("60000.00", record3[3]);

            String[] record4 = reader.readNext();
            assertEquals("4", record4[0]);
            assertEquals("Bob Brown", record4[1]);
            assertEquals("Marketing", record4[2]);
            assertEquals("45000.00", record4[3]);

            String[] record5 = reader.readNext();
            assertEquals("5", record5[0]);
            assertEquals("Emily Davis", record5[1]);
            assertEquals("IT", record5[2]);
            assertEquals("52000.00", record5[3]);
        }
    }
}
