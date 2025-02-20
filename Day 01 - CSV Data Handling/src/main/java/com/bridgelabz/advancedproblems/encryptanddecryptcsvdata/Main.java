package com.bridgelabz.advancedproblems.encryptanddecryptcsvdata;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "students.json";
        String csvFilePath = "students.csv";

        // Convert JSON to CSV with encryption
        JsonToCsvConverter.convertJsonToCsv(jsonFilePath, csvFilePath);

        // Convert CSV back to JSON with decryption
        CsvToJsonConverter.convertCsvToJson(csvFilePath, jsonFilePath);
    }
}
