package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PatientHomeScreen extends AppCompatActivity {


    private ImageButton OpenFindADoctor;
    private ImageButton OpenPrescriptions;
    private ImageButton OpenCurrAppoint;
    private ImageButton OpenPrevAppoint;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_screen);

        OpenFindADoctor = findViewById(R.id.OpenFindADoctor);
        OpenPrescriptions = findViewById(R.id.OpenPrescriptions);
        OpenCurrAppoint = findViewById(R.id.OpenCurrAppoint);
        OpenPrevAppoint = findViewById(R.id.OpenPrevAppoint);

        OpenFindADoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { FindADoctorClicked(); }
        });

        OpenPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PrescriptionsClicked(); }
        });

        OpenCurrAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { CurrAppointClicked(); }
        });

        OpenPrevAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PrevAppointClicked(); }
        });

    }

    private void FindADoctorClicked() {
    }

    private void PrescriptionsClicked() {
    }

    private void CurrAppointClicked() {
    }

    private void PrevAppointClicked() {
    }

}