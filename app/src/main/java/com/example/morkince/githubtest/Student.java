package com.example.morkince.githubtest;


import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Student {
    int idNum;
    String fName;
    String mName;
    String lName;
    String course;
    int yearLevel;

    public Student() {}

    public Student(int idNum,String fName,  String mName, String lName,String course, int yearLevel) {
        this.fName = fName;
        this.mName=mName;
        this.lName = lName;
        this.course = course;
        this.yearLevel = yearLevel;
        this.idNum = idNum;
    }



    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }


    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
}