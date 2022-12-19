package com.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.myapplication.database.StudentDatabase;
import com.myapplication.database.dao.StudentDao;
import com.myapplication.database.entities.StudentModal;

import java.util.List;

public class StudentRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private StudentDao dao;
    private LiveData<List<StudentModal>> allStudents;

    // creating a constructor for our variables
    // and passing the variables to it.
    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        dao = database.Dao();
        allStudents = dao.getAllStudents();
    }

    // creating a method to insert the data to our database.
    public void insert(StudentModal model) {
        new StudentRepository.InsertStudentAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(StudentModal model) {
        new StudentRepository.UpdateStudentAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(StudentModal model) {
        new StudentRepository.DeleteStudentAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllStudents() {
        new StudentRepository.DeleteAllStudentsAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<StudentModal>> getAllStudents() {
        return allStudents;
    }

    // we are creating a async task method to insert new course.
    private static class InsertStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;

        private InsertStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;

        private UpdateStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModal... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;

        private DeleteStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StudentModal... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllStudentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao dao;
        private DeleteAllStudentsAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllStudents();
            return null;
        }
    }
}
