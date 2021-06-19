package com.example.patientcare;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.security.PrivateKey;

public class Patient extends User implements Serializable {

    private String patientId;

    public Patient(){

    }

    public Patient(String name, String email, String phoneNumber, String address, int date, int month, int year, String password) {
        super(name, email, phoneNumber, address, date, month, year, password);
    }

    @Override
    public String toString() {
        return "Patient{} " + super.toString();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}