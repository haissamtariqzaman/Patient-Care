package com.example.patientcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signInButton;
    private Button signUpButton;
    private  final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton=findViewById(R.id.loginButton);
        signUpButton=findViewById(R.id.signupButton);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClicked();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
    }

    private void signInClicked(){

    }

    private void signUpClicked(){
        Intent i=new Intent(this,SignupActivity.class);
        startActivityForResult(i,REQUEST_CODE);
    }

}