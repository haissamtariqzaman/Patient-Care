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
    public Appointment(){

    }

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

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
}
