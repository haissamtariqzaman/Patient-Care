package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class PatientDetailActivity extends AppCompatActivity {

    String pat_id;
    TextView name, email, phone, address, dob;
    DAOPatient daoPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        getSupportActionBar().setTitle("Patient Details");

        daoPatient = new DAOPatient();
        pat_id = getIntent().getStringExtra("patient_id");

        name = findViewById(R.id.patient_name);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);

        getPatientDetails();
    }

    public void getPatientDetails() {
        DocumentReference docRef = daoPatient.db.collection("Patient").document(pat_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Patient patient = document.toObject(Patient.class);
                        name.setText(patient.getName());
                        email.setText(patient.getEmail());
                        phone.setText(patient.getPhoneNumber());
                        address.setText(patient.getAddress());
                        dob.setText(patient.getDate() + "/" + patient.getMonth() + "/" + patient.getYear());
                    } else {
//                        Log.d(TAG, "No such document");
                    }
                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}