package com.bridgelabz.advancedproblems.convertjsontocsvviceversa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            String[] header = {"ID", "Name", "Age", "Marks"};
            writer.writeNext(header);

            // Write student data to CSV
            for (Student student : students) {
                String[] record = {String.valueOf(student.getId()), student.getName(),
                        String.valueOf(student.getAge()), String.valueOf(student.getMarks())};
                writer.writeNext(record);
            }

            System.out.println("JSON data converted to CSV successfully!");

        } catch (IOException e) {
            System.out.println("Exception caught while converting JSON to CSV: " + e.getMessage());
        }
    }
}
