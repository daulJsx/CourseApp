package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_create_student extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText studentNameEdt, studentPhoneEdt, studentCourseDurationEdt;
    private Button btnSaveNewStudent, btnBackOnStudentCreateAct;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_STUDENT_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_ID";
    public static final String EXTRA_STUDENT_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_NAME";
    public static final String EXTRA_PHONE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PHONE";
    public static final String EXTRA_COURSE_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        // initializing our variables for each view.
        studentNameEdt = findViewById(R.id.etNama);
        studentPhoneEdt = findViewById(R.id.etNohp);
        studentCourseDurationEdt = findViewById(R.id.etDurasi);
        btnSaveNewStudent = findViewById(R.id.btnSaveStudent);
        btnBackOnStudentCreateAct = findViewById(R.id.btnBack);

        btnBackOnStudentCreateAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent i = new Intent(activity_create_student.this, ListDataStudent.class);
                startActivity(i);
            }
        });

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_STUDENT_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            studentNameEdt.setText(intent.getStringExtra(EXTRA_STUDENT_NAME));
            studentPhoneEdt.setText(intent.getStringExtra(EXTRA_PHONE));
            studentCourseDurationEdt.setText(intent.getStringExtra(EXTRA_COURSE_DURATION));
        }

        // adding on click listener for our save button.
        btnSaveNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String studentName = studentNameEdt.getText().toString();
                String studentPhone = studentPhoneEdt.getText().toString();
                String courseDuration = studentCourseDurationEdt.getText().toString();
                if (studentName.isEmpty() || studentPhone.isEmpty() || courseDuration.isEmpty()) {
                    Toast.makeText(activity_create_student.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveNewStudent(studentName, studentPhone, courseDuration);
            }
        });
    }

    private void saveNewStudent(String studentName, String studentPhone, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_STUDENT_NAME, studentName);
        data.putExtra(EXTRA_PHONE, studentPhone);
        data.putExtra(EXTRA_COURSE_DURATION, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_STUDENT_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_STUDENT_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "New student has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }
}