package com.bridgelabz.handsonpracticeproblems.jsonreportfromdatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        // Define JDBC URL for MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/students.db";
        // Define database username
        String username = "user";
        // Define database password
        String password = "user@123";

        // Establish database connection and execute query
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Students")) {

            // Initialize a JSON array to store the JSON objects
            JSONArray jsonArray = new JSONArray();

            // Iterate through the result set and convert each row to a JSON object
            while (resultSet.next()) {
                // Create a new JSON object
                JSONObject jsonObject = new JSONObject();
                // Put ID into the JSON object
                jsonObject.put("ID", resultSet.getInt("ID"));
                // Put Name into the JSON object
                jsonObject.put("Name", resultSet.getString("Name"));
                // Put Age into the JSON object
                jsonObject.put("Age", resultSet.getInt("Age"));
                // Put Marks into the JSON object
                jsonObject.put("Marks", resultSet.getInt("Marks"));

                // Add JSON object to JSON array
                jsonArray.put(jsonObject);
            }

            // Print the JSON array with indentation
            System.out.println(jsonArray.toString(4));

        } catch (Exception e) {
            // Print stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
