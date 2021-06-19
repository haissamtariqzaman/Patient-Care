package com.example.patientcare;

import java.io.Serializable;

public class Doctor extends User implements Serializable {

    private String speciality;

    public Doctor()
    {

    }

    public Doctor(String name, String email, String phoneNumber, String address, int date, int month, int year, String password,String spc) {
        super(name, email, phoneNumber, address, date, month, year, password);
        speciality=spc;
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                "} " + super.toString();
    }
}