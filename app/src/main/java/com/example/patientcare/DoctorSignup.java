package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.jetbrains.annotations.NotNull;

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

    private Button signup;

    private String[] specialities=new String[]{"Skin Specialist","Gynecologist","Urologist","Child Specialist","Orthopedic Surgeon","Consultant Physician","ENT Specialist","Neurologist"};

    private DAODoctor daoDoctor;

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
        pass=findViewById(R.id.editTextTextPassword3);
        signup=findViewById(R.id.registerButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, specialities);
        speciality.setAdapter(adapter);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupClicked();
            }
        });

        daoDoctor=new DAODoctor();
    }

    public void signupClicked()
    {
        String d=date.getText().toString();
        Doctor doc=new Doctor(name.getText().toString(),email.getText().toString(),ph.getText().toString(),addr.getText().toString(),0,0,0,pass.getText().toString(),speciality.getSelectedItem().toString());
        Intent intent=new Intent(this,SignupService.class);
        intent.putExtra("doctor",doc);
        intent.putExtra("date",d);
        startService(intent);

        Intent intent1=new Intent();
        setResult(RESULT_OK,intent1);
        DoctorSignup.this.onBackPressed();
    }
}