package com.bridgelabz.practiceproblems.parsejsonandfilterrecords;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class TestParseJson {

    @Test
    public void testParseAndFilter() {
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Define JSON string
            String jsonString = "[{\"name\":\"Alice\",\"age\":30,\"email\":\"alice@example.com\"}," +
                    "{\"name\":\"Bob\",\"age\":22,\"email\":\"bob@example.com\"}," +
                    "{\"name\":\"Charlie\",\"age\":28,\"email\":\"charlie@example.com\"}]";

            // Parse JSON string into a list of Person objects
            List<Person> people = objectMapper.readValue(jsonString, new TypeReference<List<Person>>(){});

            // Filter the list to include only those records where age > 25
            List<Person> filteredPeople = people.stream()
                    .filter(person -> person.getAge() > 25)
                    .collect(Collectors.toList());

            // Perform assertions to check if the filtering is correct
            assertEquals(2, filteredPeople.size(), "There should be 2 people with age > 25");

            // Check the details of the filtered people
            assertEquals("Alice", filteredPeople.get(0).getName());
            assertEquals(30, filteredPeople.get(0).getAge());
            assertEquals("alice@example.com", filteredPeople.get(0).getEmail());

            assertEquals("Charlie", filteredPeople.get(1).getName());
            assertEquals(28, filteredPeople.get(1).getAge());
            assertEquals("charlie@example.com", filteredPeople.get(1).getEmail());

        } catch (Exception e) {
            fail("Exception caught! " + e.getMessage());
        }
    }
}
