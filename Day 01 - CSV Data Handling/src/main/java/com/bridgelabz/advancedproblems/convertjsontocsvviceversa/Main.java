package com.bridgelabz.advancedproblems.convertjsontocsvviceversa;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "students.json";
        String csvFilePath = "students.csv";

        // Convert JSON to CSV
        JsonToCsvConverter.convertJsonToCsv(jsonFilePath, csvFilePath);

        // Convert CSV back to JSON
        CsvToJsonConverter.convertCsvToJson(csvFilePath, jsonFilePath);
    }
}
