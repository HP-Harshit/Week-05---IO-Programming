package com.bridgelabz.advancedproblems.generateacsvreportfromdatabase;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "user";
        String password = "User@123";

        // Path to the output CSV file
        String csvFilePath = "employee-report.csv";

        // Fetch employee records and write to CSV
        generateCSVReport(jdbcUrl, username, password, csvFilePath);
    }

    public static void generateCSVReport(String jdbcUrl, String username, String password, String csvFilePath) {
        String sqlQuery = "SELECT EmployeeID, Name, Department, Salary FROM employees";

        // Using try-with-resources to ensure resources are closed automatically
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery);
             CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

            // Write CSV header
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            // Write employee records to CSV
            while (resultSet.next()) {
                String employeeID = resultSet.getString("EmployeeID");
                String name = resultSet.getString("Name");
                String department = resultSet.getString("Department");
                String salary = resultSet.getString("Salary");

                String[] record = {employeeID, name, department, salary};
                writer.writeNext(record);
            }

            System.out.println("CSV report generated successfully!");

        } catch (Exception e) {
            // Handle exceptions and print the error message
            System.out.println("Exception caught while generating CSV report: " + e.getMessage());
        }
    }
}
