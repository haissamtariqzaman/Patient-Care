package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DoctorSignup extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText ph;
    private EditText date;
    private EditText addr;
    private EditText pass;
    private Spinner speciality;

    private String[] specialities=new String[]{"Skin Specialist","Gynecologist","Urologist","Child Specialist","Orthopedic Surgeon","Consultant Physician","ENT Specialist","Neurologist"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress2);
        ph=findViewById(R.id.editTextNumber);
        date=findViewById(R.id.editTextDate);
        addr=findViewById(R.id.editTextTextPostalAddress);
        speciality=findViewById(R.id.speciality);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, specialities);
        speciality.setAdapter(adapter);
    }

    public void signupClicked()
    {
        String d=date.getText().toString();
        int dt=0,month=0,year=0;

        StringBuilder sb=new StringBuilder();

        for(int x=0;x<d.length();x++) {
            if (d.charAt(x) == '/') {
                if (dt == 0) {
                    dt = parseInt(sb.toString());
                    sb = new StringBuilder();
                } else {
                    month = parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb = sb.append(d.charAt(x));
            }
        }
        year=parseInt(sb.toString());

        Doctor doc=new Doctor(name.getText().toString(),email.getText().toString(),ph.getText().toString(),addr.getText().toString(),dt,month,year,"123",speciality.getSelectedItem().toString());
        Log.d("mydchck",doc.toString());
    }
}