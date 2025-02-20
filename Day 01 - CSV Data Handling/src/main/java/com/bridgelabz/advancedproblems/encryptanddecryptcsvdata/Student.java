package com.bridgelabz.advancedproblems.encryptanddecryptcsvdata;

public class Student {
    private int id;
    private String name;
    private int age;
    private int marks;
    private double salary;
    private String email;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // toString method for printing
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                '}';
    }
}

