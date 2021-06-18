package com.example.patientcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DAOSignin {
    private final FirebaseFirestore db;
    private final FirebaseAuth mAuth;

    public DAOSignin()
    {
        db= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> authenticate(String email, String password)
    {
        return mAuth.signInWithEmailAndPassword(email,password);
    }

    public String getCurrentUser()
    {
        return mAuth.getCurrentUser().getUid();
    }
}