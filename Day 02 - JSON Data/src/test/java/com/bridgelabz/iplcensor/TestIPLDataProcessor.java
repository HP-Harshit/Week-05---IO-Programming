package com.bridgelabz.iplcensor;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIPLDataProcessor {

    // Test for masking team names
    @Test
    public void testMaskTeamName() {
        IPLDataProcessor processor = new IPLDataProcessor();
        // Mask team name "Mumbai Indians"
        String maskedName = processor.maskTeamName("Mumbai Indians");
        assertEquals("Mumbai ***", maskedName);
        // Mask team name "Chennai Super Kings"
        maskedName = processor.maskTeamName("Chennai Super Kings");
        assertEquals("Chennai *** ***", maskedName);
    }

    // Test for censoring JSON match data
    @Test
    public void testCensorJsonMatch() {
        IPLDataProcessor processor = new IPLDataProcessor();
        JSONObject match = new JSONObject();
        // Set team1 name
        match.put("team1", "Mumbai Indians");
        // Set team2 name
        match.put("team2", "Chennai Super Kings");
        // Set player of the match
        match.put("player_of_match", "MS Dhoni");

        // Censor JSON match data
        JSONObject censoredMatch = processor.censorJsonMatch(match);

        assertEquals("Mumbai ***", censoredMatch.getString("team1"));
        assertEquals("Chennai *** ***", censoredMatch.getString("team2"));
        assertEquals("REDACTED", censoredMatch.getString("player_of_match"));
    }

    // Test for censoring CSV fields
    @Test
    public void testCensorCsvFields() {
        IPLDataProcessor processor = new IPLDataProcessor();
        // Define CSV fields
        String[] fields = {"101", "Mumbai Indians", "Chennai Super Kings", "178", "182", "Chennai Super Kings", "MS Dhoni"};
        // Censor CSV fields
        String[] censoredFields = processor.censorCsvFields(fields);

        assertEquals("Mumbai ***", censoredFields[1]);
        assertEquals("Chennai *** ***", censoredFields[2]);
        assertEquals("REDACTED", censoredFields[6]);
    }

    // Test for processing JSON file
    @Test
    public void testProcessJsonFile() throws IOException {
        IPLDataProcessor processor = new IPLDataProcessor();
        JSONHandler handler = new JSONHandler(processor);

        // Define input JSON content
        String inputJson = "[{\"team1\":\"Mumbai Indians\",\"team2\":\"Chennai Super Kings\",\"player_of_match\":\"MS Dhoni\"}]";
        // Define expected output JSON content
        String expectedOutputJson = "[{\n" +
                "    \"player_of_match\": \"REDACTED\",\n" +
                "    \"team1\": \"Mumbai ***\",\n" +
                "    \"team2\": \"Chennai *** ***\"\n" +
                "}]";

        // Create temporary input and output files
        File inputFile = File.createTempFile("input", ".json");
        File outputFile = File.createTempFile("output", ".json");

        try (FileWriter writer = new FileWriter(inputFile)) {
            // Write input JSON content to input file
            writer.write(inputJson);
        }

        // Process JSON file
        handler.processJsonFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        // Read output JSON content from output file
        StringBuilder outputJsonBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(outputFile)) {
            int i;
            while ((i = reader.read()) != -1) {
                outputJsonBuilder.append((char) i);
            }
        }

        assertEquals(expectedOutputJson, outputJsonBuilder.toString().trim());

        // Delete temporary files
        inputFile.delete();
        outputFile.delete();
    }

    // Test for processing CSV file
    @Test
    public void testProcessCsvFile() throws IOException {
        IPLDataProcessor processor = new IPLDataProcessor();
        CSVHandler handler = new CSVHandler(processor);

        // Define input CSV content
        String inputCsv = "match_id,team1,team2,score_team1,score_team2,winner,player_of_match\n" +
                "101,Mumbai Indians,Chennai Super Kings,178,182,Chennai Super Kings,MS Dhoni";
        // Define expected output CSV content
        String expectedOutputCsv = "match_id,team1,team2,score_team1,score_team2,winner,player_of_match\n" +
                "101,Mumbai ***,Chennai *** ***,178,182,Chennai Super Kings,REDACTED";

        // Create temporary input and output files
        File inputFile = File.createTempFile("input", ".csv");
        File outputFile = File.createTempFile("output", ".csv");

        try (FileWriter writer = new FileWriter(inputFile)) {
            // Write input CSV content to input file
            writer.write(inputCsv);
        }

        // Process CSV file
        handler.processCsvFile(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        // Read output CSV content from output file
        StringBuilder outputCsvBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputCsvBuilder.append(line).append("\n");
            }
        }

        assertEquals(expectedOutputCsv.trim(), outputCsvBuilder.toString().trim());

        // Delete temporary files
        inputFile.delete();
        outputFile.delete();
    }
}
