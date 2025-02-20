package com.bridgelabz.iplcensor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVHandler {

    private IPLDataProcessor dataProcessor;

    // Constructor to initialize the IPLDataProcessor
    public CSVHandler(IPLDataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    // Method to process CSV file
    public void processCsvFile(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath)) {

            // Read lines from input file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line into fields
                String[] fields = line.split(",");

                // Censor data if it's not the header row
                if (!fields[0].equals("match_id")) {
                    fields = dataProcessor.censorCsvFields(fields);
                }

                // Write censored fields to output file
                writer.write(String.join(",", fields) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
