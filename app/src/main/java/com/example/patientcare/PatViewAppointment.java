package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PatViewAppointment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;
    private ArrayList<Appointment> appointments;
    private ArrayList<Appointment> updatedAppointments;

    private DAODoctor daoDoctor;

    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_doctors);

        Intent intent=getIntent();
        appointments=(ArrayList<Appointment>) intent.getSerializableExtra("appointments");
        //doctors=(ArrayList<Doctor>)intent.getSerializableExtra("doctors");
        patient=(Patient) intent.getSerializableExtra("patient");

        daoDoctor=new DAODoctor();
        updatedAppointments=new ArrayList<>();

        loadDoctors();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter=new AppointmentAdapter(updatedAppointments);
        recyclerView.setAdapter(appointmentAdapter);
    }

    public void loadDoctors()
    {
        for(Appointment a : appointments)
        {
            daoDoctor.getDoctor(a.doctor_id).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        a.d= task.getResult().toObject(Doctor.class);
                        updatedAppointments.add(a);
                        appointmentAdapter.updateAppointments(updatedAppointments);
                        //appointmentAdapter.updateDoctors(doctors);
                        Log.d("docResult","Doctor found!");
                    }
                    else
                    {
                        Log.d("docResult","Error!");
                    }
                }
            });
        }
    }
}