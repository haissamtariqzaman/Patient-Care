package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class ViewPrescription extends AppCompatActivity {

    private TextView AppointDate;
    private TextView dr_name;
    private TextView prescription;

    private Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription);

        Intent i =getIntent();
        appointment=(Appointment) i.getSerializableExtra("appointment");

        AppointDate=findViewById(R.id.AppointDate);
        dr_name=findViewById(R.id.dr_name);
        prescription=findViewById(R.id.prescription);

        AppointDate.setText(appointment.getDate());
        prescription.setText(appointment.getPrescription());
    }
}