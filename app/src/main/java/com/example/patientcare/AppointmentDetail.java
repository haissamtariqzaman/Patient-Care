package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AppointmentDetail extends AppCompatActivity {

    Appointment appointment;

    TextView patient_name, date, time, room;
    EditText prescription;
    SwitchCompat done;

    private AppointmentDao appointmentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        appointmentDao = new AppointmentDao();
        patient_name = findViewById(R.id.patient_name);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        room = findViewById(R.id.room);
        done = findViewById(R.id.done);
        prescription = findViewById(R.id.prescription);

        appointment = (Appointment) getIntent().getSerializableExtra("appointment");

        patient_name.setText("Name: " + appointment.getPatient_name());
        date.setText("Date: " + appointment.getDate());
        time.setText("Time: " + appointment.getTime());
        room.setText("Room: " + appointment.getRoom());
        prescription.setText(appointment.getPrescription());
        done.setChecked(appointment.done);

    }

    public void UpdateAppointment(View view) {
        appointment.prescription = prescription.getText().toString();
        appointment.done = done.isChecked();
        appointmentDao.updateAppointmentData(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AppointmentDetail.this, "Appointment updated successfully!", Toast.LENGTH_LONG).show();
                        finish();//destroy the current activity and move back to previous activity
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AppointmentDetail.this, "Error occurred while updating this Appointment.", Toast.LENGTH_LONG).show();
                    }
                });
    }
}