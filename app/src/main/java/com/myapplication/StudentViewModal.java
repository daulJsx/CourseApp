package com.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myapplication.database.entities.StudentModal;

import java.util.List;

public class StudentViewModal extends AndroidViewModel {
    
    // creating a new variable for course repository.
    private StudentRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<StudentModal>> allStudents;

    // constructor for our view modal.
    public StudentViewModal(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }

    // below method is use to insert the data to our repository.
    public void insert(StudentModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(StudentModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(StudentModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllStudents() {
        repository.deleteAllStudents();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<StudentModal>> getAllStudents() {
        return allStudents;
    }
}
