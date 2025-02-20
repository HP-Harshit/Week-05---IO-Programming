package com.bridgelabz.advancedproblems.convertcsvdataintojavaobjects;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStudent {

    private static File tempFile;

    @BeforeAll
    static void setup() throws IOException {
        // Create a temporary CSV file with test data
        tempFile = Files.createTempFile("student-dataset-test", ".csv").toFile();
        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            String[] header = {"id", "name", "age", "marks"};
            String[] record1 = {"1", "Kiana Lor", "22", "76"};
            String[] record2 = {"2", "Joshua Lonaker", "22", "87"};
            String[] record3 = {"3", "Dakota Blanco", "22", "56"};
            String[] record4 = {"4", "Natasha Yarusso", "20", "98"};
            String[] record5 = {"5", "Brooke Cazares", "21", "56"};
            String[] record6 = {"6", "Rochelle Johnson", "21", "73"};
            String[] record7 = {"7", "Joey Abreu", "22", "75"};
            String[] record8 = {"8", "Preston Suarez", "22", "87"};
            String[] record9 = {"9", "Lee Dong", "24", "68"};
            String[] record10 = {"10", "Maa'iz al-Dia", "22", "96"};
            String[] record11 = {"11", "Maja Nicholson", "23", "92"};
            writer.writeNext(header);
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
            writer.writeNext(record11);
        }
    }

    @AfterAll
    static void cleanup() {
        // Delete the test CSV file after all tests are done
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testConvertCSVToStudents() {
        // Call the convertCSVToStudents method to convert the CSV data
        List<Student> students = Main.convertCSVToStudents(tempFile);

        // Expected number of students
        int expectedSize = 11;

        // Verify the number of students
        assertEquals(expectedSize, students.size(), "The number of students should match the expected size.");

        // Verify the data of the first student
        Student student1 = students.get(0);
        assertEquals(1, student1.getId(), "ID should match");
        assertEquals("Kiana Lor", student1.getName(), "Name should match");
        assertEquals(22, student1.getAge(), "Age should match");
        assertEquals(76, student1.getMarks(), "Marks should match");

        // Verify the data of the last student
        Student student11 = students.get(10);
        assertEquals(11, student11.getId(), "ID should match");
        assertEquals("Maja Nicholson", student11.getName(), "Name should match");
        assertEquals(23, student11.getAge(), "Age should match");
        assertEquals(92, student11.getMarks(), "Marks should match");
    }
}
