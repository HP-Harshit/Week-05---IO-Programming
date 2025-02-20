package com.bridgelabz.practiceproblems.readajsonfile;

import java.util.Map;

// Class representing a Student object with various fields
public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Map<String, String> address;

    // Default constructor required for Jackson
    public Student() {}

    // Parameterized constructor
    public Student(int id, String name, String email, String phone, Map<String, String> address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters for Student object fields
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Map<String, String> getAddress() {
        return address;
    }
}
