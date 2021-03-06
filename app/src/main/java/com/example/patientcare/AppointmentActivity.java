package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity implements AppointmentsAdapter.AppointmentClickListener {

    RecyclerView appointmentsRv;
    AppointmentsAdapter adapter;
    List<Appointment> appointmentList;
    private AppointmentDao appointmentDao;
    boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        appointmentDao = new AppointmentDao();

        Intent in = getIntent();
        isDone = in.getBooleanExtra("isDone", false);

        if (in.hasExtra("name")) {
            getSupportActionBar().setTitle(in.getStringExtra("name"));
        } else {
            getSupportActionBar().setTitle("Appointments");
        }

        appointmentsRv = findViewById(R.id.appointments_rv);
        appointmentsRv.setHasFixedSize(true);
        appointmentsRv.setLayoutManager(new LinearLayoutManager(this));
        appointmentList = new ArrayList<Appointment>();
        adapter = new AppointmentsAdapter(appointmentList, this);
        appointmentsRv.setAdapter(adapter);

        getAppointments(isDone);

    }

    @Override
    public void onResume() {
        super.onResume();
        getAppointments(isDone);
    }

    public void getAppointments(boolean isDone) {
        appointmentDao.getAppointments(isDone)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            appointmentList.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Appointment appointment = new Appointment(
                                        document.get("patient_name").toString(),
                                        document.get("patient_id").toString(),
                                        document.get("doctor_id").toString(),
                                        document.get("date").toString(),
                                        document.get("time").toString(),
                                        document.get("room").toString(),
                                        document.get("prescription").toString(),
                                        (boolean) document.get("done")
                                );
                                appointment.setAppointment_id(document.getId());
                                appointmentList.add(appointment);
                            }

                            adapter.notifyDataSetChanged();

                        } else {
                            Log.d("Appointments Read Error", "Error getting appointments: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onAppointmentClick(Appointment appointment) {
        Intent intent = new Intent(this, AppointmentDetail.class);
        intent.putExtra("appointment", appointment);
        startActivity(intent);
    }

    @Override
    public void onPatientClick(Appointment appointment) {
        Intent intent = new Intent(this, PatientDetailActivity.class);
        intent.putExtra("patient_id", appointment.patient_id);
        startActivity(intent);
    }
}