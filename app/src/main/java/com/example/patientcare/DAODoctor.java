package com.example.patientcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DAODoctor {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAODoctor()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> addDoctor(Doctor d)
    {
        return mAuth.createUserWithEmailAndPassword(d.getEmail(),d.getPassword());
    }

    public Task<Void> addDoctorData(Doctor d)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",d.getName());
        map.put("email",d.getEmail());
        map.put("phoneNumber",d.getPhoneNumber());
        map.put("address",d.getAddress());
        map.put("date",d.getDate());
        map.put("month",d.getMonth());
        map.put("year",d.getYear());
        map.put("speciality",d.getSpeciality());

        return db.collection("Doctor").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map);
    }
}