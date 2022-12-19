package com.myapplication.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myapplication.database.entities.StudentModal;

import java.util.List;

public interface StudentDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(StudentModal model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(StudentModal model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(StudentModal model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM student_table")
    void deleteAllStudents();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM student_table ORDER BY student_name ASC")
    LiveData<List<StudentModal>> getAllStudents();
}
