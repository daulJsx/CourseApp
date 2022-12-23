package com.myapplication.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "student_table")
public class StudentModal {

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int studentId;

    // below line is a variable
    // for student name.
    private String studentName;

    private String studentPhone;

//    private String registrationDate;

    private String courseDuration;

    // below line we are creating constructor class.
    // inside constructor class we are not passing
    // our id because it is incrementing automatically
    public StudentModal(String studentName,String studentPhone,String courseDuration) {
        this.studentName = studentName;
        this.studentPhone = studentPhone;
//        this.registrationDate= registrationDate;
        this.courseDuration = courseDuration;
    }


    // on below line we are creating
    // getter and setter methods.
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone;}

//    public String getStartDate() {
//        return start_date;
//    }
//
//    public void setStartDate(String start_date) { this.start_date = start_date;}

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
