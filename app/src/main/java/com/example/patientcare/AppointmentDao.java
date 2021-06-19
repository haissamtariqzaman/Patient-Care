package com.example.patientcare;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentDao {
    private final FirebaseFirestore db;

    public AppointmentDao() {
        db = FirebaseFirestore.getInstance();
    }

    public Task<Void> updateAppointmentData(Appointment a) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", a.getDate());
        map.put("doctor_id", a.getDoctor_id());
        map.put("done", a.isDone());
        map.put("patient_id", a.getPatient_id());
        map.put("patient_name", a.getPatient_name());
        map.put("prescription", a.getPrescription());
        map.put("room", a.getRoom());
        map.put("time", a.getTime());

        return db.collection("Appointment").document(a.getAppointment_id()).set(map);
    }

    public Task<DocumentReference> addAppointmentData(Appointment a) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", a.getDate());
        map.put("doctor_id", a.getDoctor_id());
        map.put("done", a.isDone());
        map.put("patient_id", a.getPatient_id());
        map.put("patient_name", a.getPatient_name());
        map.put("prescription", a.getPrescription());
        map.put("room", a.getRoom());
        map.put("time", a.getTime());

        return db.collection("Appointment").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        a.setAppointment_id( documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Appointment Add Error", "Error adding appointment", e);
                    }
                });
    }

    public Task<QuerySnapshot> getAppointments(boolean isDone)
    {
        return db.collection("Appointment").whereEqualTo("done", isDone).get();
    }

    public Task<QuerySnapshot> getDoctorId(Doctor d) {
        return db.collection("Doctor").whereEqualTo("email", d.getEmail()).get();
    }

    public Task<QuerySnapshot> getAppointments(String id,String date){
        return db.collection("Appointment").whereEqualTo("doctor_id", id).whereEqualTo("date",date).get();
    }
}
