package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorHomeScreen extends AppCompatActivity {

    private Button OpenCurrAppoint;
    private Button OpenPrevAppoint;
    Doctor doctor;
    TextView welcom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);

        doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        OpenCurrAppoint = findViewById(R.id.OpenCurrAppoint);
        OpenPrevAppoint = findViewById(R.id.OpenPrevAppoint);
        welcom = findViewById(R.id.doctor_welcome);
        welcom.setText("Welcome " + doctor.getName());

        OpenCurrAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { CurrAppointClicked(); }
        });

        OpenPrevAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PrevAppointClicked(); }
        });
    }

    private void CurrAppointClicked() {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("name", "Current Appointments");
        intent.putExtra("isDone", false);
        startActivity(intent);
    }

    private void PrevAppointClicked() {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("name", "Previous Appointments");
        intent.putExtra("isDone", true);
        startActivity(intent);
    }
}