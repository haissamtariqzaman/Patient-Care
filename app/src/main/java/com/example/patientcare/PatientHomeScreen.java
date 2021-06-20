package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.concurrent.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PatientHomeScreen extends AppCompatActivity {
    private Button OpenFindADoctor;
    private Button OpenPrescriptions;
    private Button OpenCurrAppoint;
    private Button OpenPrevAppoint;

    private Patient patient;

    private ArrayList<Appointment> currAppointments;
    private ArrayList<Appointment> prevAppointments;

    private AppointmentDao appointmentDao;

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

        currAppointments=new ArrayList<>();
        prevAppointments=new ArrayList<>();

        appointmentDao=new AppointmentDao();

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
        currAppointments.clear();
        appointmentDao.getAppointmentsCurrent(patient).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot d:task.getResult())
                    {
                        Appointment a=d.toObject(Appointment.class);
                        currAppointments.add(a);
                    }

                    Intent i=new Intent(PatientHomeScreen.this,PatViewAppointment.class);
                    i.putExtra("appointments",currAppointments);
                    i.putExtra("patient",patient);
                    startActivityForResult(i,1);
                }
            }
        });
    }

    private void PrevAppointClicked() {
        prevAppointments.clear();
        appointmentDao.getAppointmentsPrev(patient).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot d:task.getResult())
                    {
                        Appointment a=d.toObject(Appointment.class);
                        prevAppointments.add(a);
                    }

                    Intent i=new Intent(PatientHomeScreen.this,PatViewAppointment.class);
                    i.putExtra("appointments",prevAppointments);
                    i.putExtra("patient",patient);
                    startActivityForResult(i,1);
                }
            }
        });
    }
}