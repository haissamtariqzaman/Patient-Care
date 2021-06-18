package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AppointmentActivity extends AppCompatActivity {

    RecyclerView appointmentsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Intent in = getIntent();

        if (in.hasExtra("name")) {
            getSupportActionBar().setTitle(in.getStringExtra("name"));
        } else {
            getSupportActionBar().setTitle("Appointments");
        }

        appointmentsRv = findViewById(R.id.appointments_rv);
    }
}