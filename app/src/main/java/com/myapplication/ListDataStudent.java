package com.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapplication.adapter.StudentRVAdapter;
import com.myapplication.database.entities.StudentModal;

import java.util.List;

public class ListDataStudent extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView studentsRV;
    private static final int ADD_STUDENT_REQUEST = 1;
    private static final int EDIT_STUDENT_REQUEST = 2;
    private StudentViewModal studentViewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_student);


        // initializing our variable for our recycler view and fab.
        studentsRV = findViewById(R.id.idRVStudent);
        FloatingActionButton fab = findViewById(R.id.idFABAddNewStudent);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(ListDataStudent.this, activity_create_student.class);
                startActivityForResult(intent, ADD_STUDENT_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        studentsRV.setLayoutManager(new LinearLayoutManager(this));
        studentsRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final StudentRVAdapter adapter = new StudentRVAdapter();

        // setting adapter class for recycler view.
        studentsRV.setAdapter(adapter);

        // passing a data from view modal.
        studentViewModal = ViewModelProviders.of(this).get(StudentViewModal.class);

        // below line is use to get all the courses from view modal.
        studentViewModal.getAllStudents().observe(this, new Observer<List<StudentModal>>() {
            @Override
            public void onChanged(List<StudentModal> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                studentViewModal.delete(adapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ListDataStudent.this, "Student deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(studentsRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new StudentRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StudentModal model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(ListDataStudent.this, activity_create_student.class);
                intent.putExtra(activity_create_student.EXTRA_STUDENT_ID, model.getStudentId());
                intent.putExtra(activity_create_student.EXTRA_STUDENT_NAME, model.getStudentName());
                intent.putExtra(activity_create_student.EXTRA_PHONE, model.getStudentPhone());
                intent.putExtra(activity_create_student.EXTRA_COURSE_DURATION, model.getCourseDuration());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_STUDENT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            String studentName = data.getStringExtra(activity_create_student.EXTRA_STUDENT_NAME);
            String studentPhone = data.getStringExtra(activity_create_student.EXTRA_PHONE);
            String courseDuration = data.getStringExtra(activity_create_student.EXTRA_COURSE_DURATION);
            StudentModal model = new StudentModal(studentName, studentPhone, courseDuration);
            studentViewModal.insert(model);
            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(activity_create_student.EXTRA_STUDENT_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Course can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String studentName = data.getStringExtra(activity_create_student.EXTRA_STUDENT_NAME);
            String studentPhone = data.getStringExtra(activity_create_student.EXTRA_PHONE);
            String courseDuration = data.getStringExtra(activity_create_student.EXTRA_COURSE_DURATION);
            StudentModal model = new StudentModal(studentName, studentPhone, courseDuration);
            model.setStudentId(id);
            studentViewModal.update(model);
            Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course not saved", Toast.LENGTH_SHORT).show();
        }
    }
}