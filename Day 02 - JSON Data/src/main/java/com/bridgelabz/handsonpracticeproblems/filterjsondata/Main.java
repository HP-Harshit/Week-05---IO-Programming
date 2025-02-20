package com.bridgelabz.handsonpracticeproblems.filterjsondata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
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

            // Print the filtered list
            for(Person person : filteredPeople) {
                System.out.println("Name: " + person.getName());
                System.out.println("Age: " + person.getAge());
                System.out.println("Email: " + person.getEmail());
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the parsing and filtering process
            System.out.println("Exception caught! " + e.getMessage());
        }
    }
}
