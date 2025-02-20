package com.bridgelabz.advancedproblems.encryptanddecryptcsvdata;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class JsonToCsvConverter {
    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath);
             CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

            // Read JSON file
            Gson gson = new Gson();
            Type studentListType = new TypeToken<List<Student>>(){}.getType();
            List<Student> students = gson.fromJson(reader, studentListType);

            // Write CSV header
            String[] header = {"ID", "Name", "Age", "Marks", "Salary", "Email"};
            writer.writeNext(header);

            // Write student data to CSV with encryption
            for (Student student : students) {
                String encryptedSalary = CryptoUtils.encrypt(String.valueOf(student.getSalary()));
                String encryptedEmail = CryptoUtils.encrypt(student.getEmail());
                String[] record = {String.valueOf(student.getId()), student.getName(),
                        String.valueOf(student.getAge()), String.valueOf(student.getMarks()),
                        encryptedSalary, encryptedEmail};
                writer.writeNext(record);
            }

            System.out.println("JSON data converted to CSV with encryption successfully!");

        } catch (Exception e) {
            System.out.println("Exception caught while converting JSON to CSV: " + e.getMessage());
        }
    }
}