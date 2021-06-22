package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signInButton;
    private Button signUpButton;

    private DAOSignin daoSignin;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton=findViewById(R.id.loginButton);
        signUpButton=findViewById(R.id.signupButton);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);

        //Admob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClicked();
            }
        });

        daoSignin=new DAOSignin();
    }

    private void signInClicked(){
        daoSignin.authenticate(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    daoSignin.checkPatient().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            Patient p=null;

                            for(DocumentSnapshot d:task.getResult())
                            {
                                p=d.toObject(Patient.class);
                                p.setPatientId(d.getId());
                            }

                            if(p!=null)
                            {
                                Intent i=new Intent(MainActivity.this,PatientHomeScreen.class);
                                i.putExtra("patient",p);
                                startActivity(i);
                            }
                        }
                    });

                    daoSignin.checkDoctor().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            Doctor doctor = null;

                            for(DocumentSnapshot d:task.getResult())
                            {
                                doctor = d.toObject(Doctor.class);
                                doctor.setDocId(d.getId());
                            }

                            if(doctor!=null)
                            {
                                Intent i=new Intent(MainActivity.this, DoctorHomeScreen.class);
                                i.putExtra("doctor",doctor);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Signin Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signUpClicked(){
        Intent i=new Intent(this,SignupActivity.class);
        startActivityForResult(i,1);
    }
}