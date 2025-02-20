package com.bridgelabz.iplcensor;

import org.json.JSONObject;

public class IPLDataProcessor {

    // Method to mask team name
    public String maskTeamName(String teamName) {
        // Split team name into parts
        String[] parts = teamName.split(" ");
        // Check if team name has more than one part
        if (parts.length > 1) {
            // Initialize masked name with the first part
            StringBuilder maskedName = new StringBuilder(parts[0]);
            // Mask remaining parts with "***"
            for (int i = 1; i < parts.length; i++) {
                maskedName.append(" ***");
            }
            // Return masked name as a string
            return maskedName.toString();
        }
        // Return original team name if it has only one part
        return teamName;
    }

    // Method to censor JSON match data
    public JSONObject censorJsonMatch(JSONObject match) {
        // Mask team1 name
        match.put("team1", maskTeamName(match.getString("team1")));
        // Mask team2 name
        match.put("team2", maskTeamName(match.getString("team2")));
        // Redact player of the match
        match.put("player_of_match", "REDACTED");
        // Return censored match data
        return match;
    }

    // Method to censor CSV fields
    public String[] censorCsvFields(String[] fields) {
        // Check if the fields array has the expected number of elements
        if (fields.length >= 7) {
            // Mask team1 name
            fields[1] = maskTeamName(fields[1]);
            // Mask team2 name
            fields[2] = maskTeamName(fields[2]);
            // Redact player of the match
            fields[6] = "REDACTED";
        }
        // Return censored fields
        return fields;
    }
}
