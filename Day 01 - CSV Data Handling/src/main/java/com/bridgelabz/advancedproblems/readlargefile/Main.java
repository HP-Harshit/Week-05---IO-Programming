package com.bridgelabz.advancedproblems.readlargefile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Path to the large CSV file
        File largeCSVFile = new File("large-dataset.csv");

        // Process the large CSV file efficiently
        processLargeCSVFile(largeCSVFile, 100);
    }

    public static void processLargeCSVFile(File file, int chunkSize) {
        int totalRecordsProcessed = 0;

        // Using try-with-resources to ensure the reader is closed automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int linesProcessed = 0;

            // Skip the header line if needed
            String header = reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Process the line (Here we just count it)
                linesProcessed++;
                totalRecordsProcessed++;

                // Display the count of records processed after each chunk
                if (linesProcessed >= chunkSize) {
                    System.out.println("Processed " + linesProcessed + " lines. Total records processed: " + totalRecordsProcessed);
                    // Reset the chunk counter
                    linesProcessed = 0;
                }
            }

            // Display remaining lines if less than chunk size
            if (linesProcessed > 0) {
                System.out.println("Processed " + linesProcessed + " lines. Total records processed: " + totalRecordsProcessed);
            }

        } catch (IOException e) {
            // Handle exceptions and print the error message
            System.out.println("Exception caught while reading the CSV file: " + e.getMessage());
        }
    }
}
