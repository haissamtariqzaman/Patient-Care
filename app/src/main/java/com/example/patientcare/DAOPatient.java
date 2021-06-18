package com.example.patientcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;

public class DAOPatient {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAOPatient()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> addPatient(Patient p)
    {
        return mAuth.createUserWithEmailAndPassword(p.getEmail(),p.getPassword());
    }

    public Task<Void> addPatientData(Patient p)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",p.getName());
        map.put("email",p.getEmail());
        map.put("phoneNumber",p.getPhoneNumber());
        map.put("address",p.getAddress());
        map.put("date",p.getDate());
        map.put("month",p.getMonth());
        map.put("year",p.getYear());

        return db.collection("Patient").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map);
    }
}