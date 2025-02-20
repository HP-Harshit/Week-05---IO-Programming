package com.bridgelabz.basicproblems.writedatatoacsvfile;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        // Define the file name for the new CSV file
        File file = new File("employee-dataset.csv");

        // Create CSVWriter using FileWriter to write to the file
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            // Write the header to the CSV file
            String[] header = {"ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            // Write records to the CSV file
            String[] record1 = {"1", "John Doe", "IT", "2199.99"};
            String[] record2 = {"2", "Jane Smith", "CSE", "1499.99"};
            String[] record3 = {"3", "Alice Johnson", "Sales", "1599.99"};
            String[] record4 = {"4", "Joey Abreu", "Marketing", "1799.99"};
            String[] record5 = {"5", "Kiana Lor", "HR", "2599.99"};
            String[] record6 = {"6", "Michael Brown", "IT", "2200.00"};
            String[] record7 = {"7", "Rachel Green", "Sales", "1600.00"};
            String[] record8 = {"8", "David Lee", "Marketing", "1800.00"};
            String[] record9 = {"9", "Monica Geller", "CSE", "1500.00"};
            String[] record10 = {"10", "Phoebe Buffay", "HR", "2600.00"};
            writer.writeNext(record1);
            writer.writeNext(record2);
            writer.writeNext(record3);
            writer.writeNext(record4);
            writer.writeNext(record5);
            writer.writeNext(record6);
            writer.writeNext(record7);
            writer.writeNext(record8);
            writer.writeNext(record9);
            writer.writeNext(record10);

            // Print success message
            System.out.println("File written successfully.");
        } catch (Exception e) {
            // Handle exceptions related to writing the file
            System.out.println("Exception caught while writing the file! " + e.getMessage());
        }
    }
}
