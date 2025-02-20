package com.bridgelabz.handsonpracticeproblems.jsonreportfromdatabase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseToJsonReportTest {

    @Test
    public void testJsonReportFromDatabase() {
        // Define JDBC URL for MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/students.db";
        // Define database username
        String username = "user";
        // Define database password
        String password = "user@123";
        // Define expected JSON output
        String expectedJsonOutput = "[\n" +
                "    {\n" +
                "        \"ID\": \"1\",\n" +
                "        \"Marks\": \"76\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Kiana Lor\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"2\",\n" +
                "        \"Marks\": \"87\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Joshua Lonaker\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"3\",\n" +
                "        \"Marks\": \"56\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Dakota Blanco\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"4\",\n" +
                "        \"Marks\": \"98\",\n" +
                "        \"Age\": \"20\",\n" +
                "        \"Name\": \"Natasha Yarusso\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"5\",\n" +
                "        \"Marks\": \"56\",\n" +
                "        \"Age\": \"21\",\n" +
                "        \"Name\": \"Brooke Cazares\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"6\",\n" +
                "        \"Marks\": \"73\",\n" +
                "        \"Age\": \"21\",\n" +
                "        \"Name\": \"Rochelle Johnson\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"7\",\n" +
                "        \"Marks\": \"75\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Joey Abreu\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"8\",\n" +
                "        \"Marks\": \"87\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Preston Suarez\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"9\",\n" +
                "        \"Marks\": \"68\",\n" +
                "        \"Age\": \"24\",\n" +
                "        \"Name\": \"Lee Dong\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"10\",\n" +
                "        \"Marks\": \"96\",\n" +
                "        \"Age\": \"22\",\n" +
                "        \"Name\": \"Maa'iz al-Dia\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"ID\": \"11\",\n" +
                "        \"Marks\": \"92\",\n" +
                "        \"Age\": \"23\",\n" +
                "        \"Name\": \"Maja Nicholson\"\n" +
                "    }\n" +
                "]";

        // Initialize a JSON array to store the JSON objects
        JSONArray jsonArray = new JSONArray();

        // Establish database connection and execute query
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Students")) {

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

        } catch (Exception e) {
            // Print stack trace if an exception occurs
            e.printStackTrace();
        }

        // Assert that the JSON array matches the expected JSON output
        assertEquals(expectedJsonOutput.trim(), jsonArray.toString(4).trim());
    }
}
