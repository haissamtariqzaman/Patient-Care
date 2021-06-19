package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerView_Doctors extends AppCompatActivity implements DoctorAdapter.onClickListener{

    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private ArrayList<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_doctors);

        Intent intent=getIntent();
        doctors=(ArrayList<Doctor>) intent.getSerializableExtra("doctors");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorAdapter=new DoctorAdapter(doctors,this,this);
        recyclerView.setAdapter(doctorAdapter);
    }

    @Override
    public void onDoctorClicked(int index) {
        Doctor d=doctors.get(index);
        Intent i=new Intent(this, PatAppointmentActivity.class);
        i.putExtra("doctor",d);
        startActivityForResult(i,1);
    }
}