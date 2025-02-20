package com.bridgelabz.advancedproblems.encryptanddecryptcsvdata;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvToJsonConverter {
    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath));
             FileWriter writer = new FileWriter(jsonFilePath)) {

            // Read CSV file
            List<Student> students = new ArrayList<>();
            String[] nextLine;
            boolean isFirstLine = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header line
                    continue;
                }

                Student student = new Student();
                student.setId(Integer.parseInt(nextLine[0]));
                student.setName(nextLine[1]);
                student.setAge(Integer.parseInt(nextLine[2]));
                student.setMarks(Integer.parseInt(nextLine[3]));
                student.setSalary(Double.parseDouble(CryptoUtils.decrypt(nextLine[4])));
                student.setEmail(CryptoUtils.decrypt(nextLine[5]));

                students.add(student);
            }

            // Convert list of students to JSON
            Gson gson = new Gson();
            String json = gson.toJson(students);

            // Write JSON to file
            writer.write(json);

            System.out.println("CSV data converted to JSON with decryption successfully!");

        } catch (Exception e) {
            System.out.println("Exception caught while converting CSV to JSON: " + e.getMessage());
        }
    }
}