package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PatientHomeScreen extends AppCompatActivity {
    private Button OpenFindADoctor;
    private Button OpenPrescriptions;
    private Button OpenCurrAppoint;
    private Button OpenPrevAppoint;

    private Patient patient;

    private final int requestCode_findDoctor;

    public PatientHomeScreen()
    {
        requestCode_findDoctor=1;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_screen);

        Intent i=getIntent();
        patient=(Patient) i.getSerializableExtra("patient");

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
        Intent i=new Intent(this,FindDoctor.class);
        i.putExtra("patient",patient);
        startActivityForResult(i,requestCode_findDoctor);
    }

    private void PrescriptionsClicked() {
    }

    private void CurrAppointClicked() {
    }

    private void PrevAppointClicked() {
    }
}