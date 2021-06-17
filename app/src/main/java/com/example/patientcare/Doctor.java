package com.example.patientcare;

public class Doctor extends User {

    private String speciality;

    public Doctor(String name, String email, String phoneNumber, String address, int date, int month, int year, String password,String spc) {
        super(name, email, phoneNumber, address, date, month, year, password);
        speciality=spc;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                "} " + super.toString();
    }
}