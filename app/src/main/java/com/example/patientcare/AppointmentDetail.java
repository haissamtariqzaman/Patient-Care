package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppointmentDetail extends AppCompatActivity {

    Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        appointment = (Appointment) getIntent().getSerializableExtra("appointment");
    }
}