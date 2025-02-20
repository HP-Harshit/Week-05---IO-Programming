package com.bridgelabz.advancedproblems.validatecsvdata;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Specify the path of the CSV file
        File filePath = new File("validate-employee-dataset.csv");
        validateCSVData(filePath); // Validate CSV data
    }

    public static void validateCSVData(File filePath) {
        // Regular expression for validating email
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        // Regular expression for validating phone number
        String phoneRegex = "^\\+?(91)?[6-9]\\d{9}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);

        // Using try-with-resources to ensure the reader is closed automatically
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            // Track line number
            int lineNumber = 0;

            // Looping through each line of the CSV file
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;

                // Skip header row
                if (lineNumber == 1) {
                    continue;
                }

                String email = nextLine[2];
                String phone = nextLine[3];

                // Check if email and phone number are valid
                boolean isEmailValid = emailPattern.matcher(email).matches();
                boolean isPhoneValid = phonePattern.matcher(phone).matches();

                // If any data is invalid, print an error message
                if (!isEmailValid || !isPhoneValid) {
                    System.out.println("Invalid data found in line " + (lineNumber-1) + ": " +
                            String.join(",", nextLine));
                    if (!isEmailValid) {
                        System.out.println("Invalid Email: " + email);
                    }
                    if (!isPhoneValid) {
                        System.out.println("Invalid Phone Number: " + phone);
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Handling exceptions and printing the error message
            System.out.println("Exception caught! " + e.getMessage()); // Exception caught
        }
    }
}
