package com.example.patientcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPatient {
    private final DatabaseReference databaseReference;

    public DAOPatient()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Patient.class.getSimpleName());
    }

    public Task<Void> addPatient(Patient p)
    {
        if (p!=null)
        {
            return databaseReference.push().setValue(p);
        }
        return null;
    }
}