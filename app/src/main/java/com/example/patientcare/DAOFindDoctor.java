package com.example.patientcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DAOFindDoctor {
    private final FirebaseFirestore db;

    public DAOFindDoctor()
    {
        db= FirebaseFirestore.getInstance();
    }

    public Task<QuerySnapshot> getDoctors(String speciality)
    {
        return db.collection("Doctor").whereEqualTo("speciality",speciality).get();
    }
}