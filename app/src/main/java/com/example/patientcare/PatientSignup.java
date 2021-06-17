package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class PatientSignup extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText ph;
    private EditText date;
    private EditText addr;
    private EditText pass;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress2);
        ph=findViewById(R.id.editTextNumber);
        date=findViewById(R.id.editTextDate);
        addr=findViewById(R.id.editTextTextPostalAddress);
        signup=findViewById(R.id.registerButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupClicked();
            }
        });
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

        Patient pat=new Patient(name.getText().toString(),email.getText().toString(),ph.getText().toString(),addr.getText().toString(),dt,month,year,"123");
        Log.d("mydchck",pat.toString());
    }
}