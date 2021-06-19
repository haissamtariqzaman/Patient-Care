package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FindDoctor extends AppCompatActivity implements View.OnClickListener {

    private Button skin;
    private  Button gyne;
    private Button urologist;
    private Button child;
    private Button ortho;
    private Button consultant;
    private  Button ent;
    private Button nuero;

    private Patient patient;

    private DAOFindDoctor daoFindDoctor;

    private ArrayList<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speciality_ui);

        Intent i=getIntent();
        patient=(Patient) i.getSerializableExtra("patient");

        skin=findViewById(R.id.button_skin);
        gyne=findViewById(R.id.button_gyne);
        urologist=findViewById(R.id.button_urologist);
        child=findViewById(R.id.button_child);
        ortho=findViewById(R.id.button_ortho);
        consultant=findViewById(R.id.button_consultant);
        ent=findViewById(R.id.button_ent);
        nuero=findViewById(R.id.button_neuro);

        skin.setOnClickListener(this);
        gyne.setOnClickListener(this);
        urologist.setOnClickListener(this);
        child.setOnClickListener(this);
        ortho.setOnClickListener(this);
        consultant.setOnClickListener(this);
        ent.setOnClickListener(this);
        nuero.setOnClickListener(this);

        daoFindDoctor=new DAOFindDoctor();
        doctors=new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_skin:
                Clicked("Skin Specialist");
                break;
            case R.id.button_gyne:
                Clicked("Gynecologist");
                break;
            case R.id.button_urologist:
                Clicked("Urologist");
                break;
            case R.id.button_child:
                Clicked("Child Specialist");
                break;
            case R.id.button_ortho:
                Clicked("Orthopedic Surgeon");
                break;
            case R.id.button_consultant:
                Clicked("Consultant Physician");
                break;
            case R.id.button_ent:
                Clicked("ENT Specialist");
                break;
            case R.id.button_neuro:
                Clicked("Neurologist");
                break;
        }
    }

    public void Clicked(String s)
    {
        daoFindDoctor.getDoctors(s).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    doctors.clear();

                    for(QueryDocumentSnapshot doc : task.getResult())
                    {
                        Doctor d=doc.toObject(Doctor.class);
                        doctors.add(d);
                    }

                    Intent i=new Intent(FindDoctor.this,RecyclerView_Doctors.class);
                    i.putExtra("doctors",doctors);
                    i.putExtra("patient",patient);
                    startActivityForResult(i,1);
                }
            }
        });
    }
}