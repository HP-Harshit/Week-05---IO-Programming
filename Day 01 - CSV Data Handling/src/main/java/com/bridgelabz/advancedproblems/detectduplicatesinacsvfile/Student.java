package com.bridgelabz.advancedproblems.detectduplicatesinacsvfile;

public class Student {
    private int id;
    private String name;
    private int age;
    private int marks;
    private String grade;

    // Constructor
    public Student(int id, String name, int age, int marks, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.grade = grade;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getMarks() { return marks; }
    public String getGrade() { return grade; }

    // toString method for printing
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                ", grade='" + grade + '\'' +
                '}';
    }
}
