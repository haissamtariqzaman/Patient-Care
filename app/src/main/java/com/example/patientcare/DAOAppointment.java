package com.example.patientcare;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class DAOAppointment {
    private final FirebaseFirestore db;

    public DAOAppointment()
    {
        db= FirebaseFirestore.getInstance();
    }

    public Task<QuerySnapshot> getDoctorId(Doctor d) {
        return db.collection("Doctor").whereEqualTo("email", d.getEmail()).get();
    }

    public Task<QuerySnapshot> getAppointments(String id,String date){
        return db.collection("Appointment").whereEqualTo("doctor_id", id).whereEqualTo("date",date).get();
    }
}