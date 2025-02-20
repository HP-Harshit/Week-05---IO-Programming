package com.bridgelabz.iplcensor;

public class Main {

    public static void main(String[] args) {
        // Initialize IPLDataProcessor
        IPLDataProcessor dataProcessor = new IPLDataProcessor();

        // Initialize JSONHandler with IPLDataProcessor
        JSONHandler jsonHandler = new JSONHandler(dataProcessor);

        // Initialize CSVHandler with IPLDataProcessor
        CSVHandler csvHandler = new CSVHandler(dataProcessor);

        // Define input and output file paths for JSON and CSV
        String jsonInputFilePath = "src/main/resources/IPL-input.json";
        String jsonOutputFilePath = "src/main/resources/IPLcsv-input.csv";
        String csvInputFilePath = "src/main/resources/IPL-output.json";
        String csvOutputFilePath = "src/main/resources/IPLcsv-output.csv";

        // Process JSON file
        jsonHandler.processJsonFile(jsonInputFilePath, jsonOutputFilePath);

        // Process CSV file
        csvHandler.processCsvFile(csvInputFilePath, csvOutputFilePath);
    }
}
