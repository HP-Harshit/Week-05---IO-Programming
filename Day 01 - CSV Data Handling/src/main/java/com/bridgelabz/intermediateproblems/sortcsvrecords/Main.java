package com.bridgelabz.intermediateproblems.sortcsvrecords;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File filePath = new File("employee-dataset.csv");
        sortAndPrintTopSalaries(filePath);
    }

    public static void sortAndPrintTopSalaries(File filePath) {
        List<String[]> records = new ArrayList<>();
        String[] header = null;

        // Reading the CSV file and storing records in a list
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            boolean isFirstLine = true;
            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    header = nextLine;
                    isFirstLine = false;
                } else {
                    records.add(nextLine);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Exception caught! " + e.getMessage());
        }

        // Sorting the records by salary in descending order
        Collections.sort(records, new Comparator<String[]>() {
            @Override
            public int compare(String[] record1, String[] record2) {
                return Double.compare(Double.parseDouble(record2[3]), Double.parseDouble(record1[3]));
            }
        });

        // Printing the header
        if (header != null) {
            System.out.println(String.join("\t", header));
        }

        // Printing the top 5 highest-paid employees
        for (int i = 0; i < 5 && i < records.size(); i++) {
            System.out.println(String.join("\t", records.get(i)));
        }
    }
}
