package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    private Button doctor;
    private Button patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        doctor=findViewById(R.id.doctorButton);
        patient=findViewById(R.id.patientButton);

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorClicked();
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientClicked();
            }
        });
    }

    public void doctorClicked(){
        Intent i=new Intent(this,DoctorSignup.class);
        startActivityForResult(i,1);
    }

    public void patientClicked(){
        Intent i=new Intent(this,PatientSignup.class);
        startActivityForResult(i,1);
    }
}