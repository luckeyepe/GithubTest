package com.example.morkince.githubtest.models;

public class Student {
    private String firstName;
    private String middleName;
    private String lastName;
    private String course;
    private Integer yearLevel;
    private Integer idNumber;

    public Student(){

    }

    public Student(String firstName, String middleName, String lastName, String course, Integer yearLevel, Integer idNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.course = course;
        this.yearLevel = yearLevel;
        this.idNumber = idNumber;
    }

    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYearLevel(Integer yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public Integer getYearLevel() {
        return yearLevel;
    }

    public Integer getIdNumber() {
        return idNumber;
    }
}
