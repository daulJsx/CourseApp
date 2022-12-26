package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class activity_create_student extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText studentNameEdt, studentPhoneEdt,studentRegistration, studentCourseDurationEdt;
    private Button btnSaveNewStudent, btnBackOnStudentCreateAct;
    private ImageButton btnDate;
    private int tahun, bulan, tanggal;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_STUDENT_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_ID";
    public static final String EXTRA_STUDENT_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_NAME";
    public static final String EXTRA_PHONE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PHONE";
    public static final String EXTRA_REGIS = "com.gtappdevelopers.gfgroomdatabase.EXTRA_REGIS";
    public static final String EXTRA_COURSE_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        // initializing our variables for each view.
        studentNameEdt = findViewById(R.id.etNama);
        studentPhoneEdt = findViewById(R.id.etNohp);
        studentRegistration = findViewById(R.id.etDaftar);
        studentCourseDurationEdt = findViewById(R.id.etDurasi);
        btnSaveNewStudent = findViewById(R.id.btnSaveStudent);
        btnBackOnStudentCreateAct = findViewById(R.id.btnBack);
        btnDate = findViewById(R.id.btnDate);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                tahun = calender.get(Calendar.YEAR);
                bulan = calender.get(Calendar.MONTH);
                tanggal = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(activity_create_student.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        studentRegistration.setText(tanggal + " - " + bulan + " - " + tahun);
                    }
                },tahun, bulan, tanggal);
                dialog.show();
            }
        });


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
            studentRegistration.setText(intent.getStringExtra(EXTRA_REGIS));
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
                String studentRegis = studentRegistration.getText().toString();
                String courseDuration = studentCourseDurationEdt.getText().toString();
                if (studentName.isEmpty() || studentPhone.isEmpty() || studentRegis.isEmpty() || courseDuration.isEmpty()) {
                    Toast.makeText(activity_create_student.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveNewStudent(studentName, studentPhone, studentRegis, courseDuration);
            }
        });
    }

    private void saveNewStudent(String studentName, String studentPhone, String studentRegis, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_STUDENT_NAME, studentName);
        data.putExtra(EXTRA_PHONE, studentPhone);
        data.putExtra(EXTRA_REGIS, studentRegis);
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