package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Prescription_recycler extends AppCompatActivity implements Prescription_Adapter.onClickListener {

    private RecyclerView recyclerView;
    private Prescription_Adapter prescription_adapter;
    private ArrayList<Appointment> app;
    private ArrayList<Appointment> appointments;

    private DAODoctor daoDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_doctors);

        appointments=new ArrayList<Appointment>();
        daoDoctor=new DAODoctor();

        Intent intent=getIntent();
        app=(ArrayList<Appointment>) intent.getSerializableExtra("appointments");
        updateDoctor();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prescription_adapter=new Prescription_Adapter(appointments,this,this);
        recyclerView.setAdapter(prescription_adapter);
    }

    @Override
    public void onPrescriptionClicked(int index) {
        Appointment a=appointments.get(index);
        Intent i=new Intent(this, ViewPrescription.class);
        i.putExtra("appointment",a);
        startActivityForResult(i,1);
    }

    public void updateDoctor()
    {
        for(Appointment appointment: app){
            daoDoctor.getDoctor(appointment.doctor_id).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful())
                    {
                        appointment.d=task.getResult().toObject(Doctor.class);
                        appointments.add(appointment);
                        prescription_adapter.updateAppointments(appointments);
                    }
                }
            });
        }
    }
}