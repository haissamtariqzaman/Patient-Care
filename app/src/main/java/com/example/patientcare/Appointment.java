package com.example.patientcare;

public class Appointment {
    String patientName;
    String patientId;
    String doctorId;
    String date;
    String time;
    String room;
    boolean done;
    String prescription;

    public Appointment(String patientName, String patientId, String doctorId, String date, String time, String room) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.room = room;
        done = false;
        prescription = "";
    }
}
