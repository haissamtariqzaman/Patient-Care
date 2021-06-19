package com.example.patientcare;

import java.io.Serializable;

public class Appointment implements Serializable {
    String patient_name;
    String patient_id;
    String doctor_id;
    String date;
    String time;
    String room;
    boolean done;
    String prescription;
    String appointment_id;

    public Appointment(String patientName, String patientId, String doctorId, String date, String time, String room) {
        this.patient_name = patientName;
        this.patient_id = patientId;
        this.doctor_id = doctorId;
        this.date = date;
        this.time = time;
        this.room = room;
        done = false;
        prescription = "";
        appointment_id = "";
    }
}
