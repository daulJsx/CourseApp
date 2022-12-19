package com.myapplication;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "student_table")
public class StudentModal {

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int student_id;

    // below line is a variable
    // for student name.
    private String student_name;

    private String student_phone;

    private String start_date;

    private String course_duration;

    // below line we are creating constructor class.
    // inside constructor class we are not passing
    // our id because it is incrementing automatically
    public StudentModal(String student_name,String student_phone,String start_date,String course_duration) {
        this.student_name = student_name;
        this.student_phone = student_phone;
        this.start_date= start_date;
        this.course_duration = course_duration;
    }

    // on below line we are creating
    // getter and setter methods.
    public String getStudent_name() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudentPhone(String student_phone) { this.student_phone = student_phone;}

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) { this.start_date = start_date;}

    public String getCourseDuration() {
        return course_duration;
    }

    public void setCourseDuration(String courseDuration) {
        this.course_duration = course_duration;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int id) {
        this.student_id = student_id;
    }
}
