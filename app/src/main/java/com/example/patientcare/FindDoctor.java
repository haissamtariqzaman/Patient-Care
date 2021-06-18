package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.function.Consumer;

public class FindDoctor extends AppCompatActivity implements View.OnClickListener {

    private Button skin;
    private  Button gyne;
    private Button urologist;
    private Button child;
    private Button ortho;
    private Button consultant;
    private  Button ent;
    private Button nuero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speciality_ui);

        skin=findViewById(R.id.button_skin);
        gyne=findViewById(R.id.button_gyne);
        urologist=findViewById(R.id.button_urologist);
        child=findViewById(R.id.button_child);
        ortho=findViewById(R.id.button_ortho);
        consultant=findViewById(R.id.button_consultant);
        ent=findViewById(R.id.button_ent);
        nuero=findViewById(R.id.button_neuro);

        skin.setOnClickListener(this);
        gyne.setOnClickListener(this);
        urologist.setOnClickListener(this);
        child.setOnClickListener(this);
        ortho.setOnClickListener(this);
        consultant.setOnClickListener(this);
        ent.setOnClickListener(this);
        nuero.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_skin:
                skinClicked();
                break;
            case R.id.button_gyne:
                gyneClicked();
                break;
            case R.id.button_urologist:
                uroloClicked();
                break;
            case R.id.button_child:
                childClicked();
                break;
            case R.id.button_ortho:
                orthoClicked();
                break;
            case R.id.button_consultant:
                consultClicked();
                break;
            case R.id.button_ent:
                entClicked();
                break;
            case R.id.button_neuro:
                neuroClicked();
                break;
        }
    }

    public void skinClicked()
    {

    }
    public void gyneClicked()
    {

    }
    public void uroloClicked()
    {

    }
    public void childClicked()
    {

    }
    public void orthoClicked()
    {

    }
    public void consultClicked()
    {

    }
    public void entClicked()
    {

    }
    public void neuroClicked()
    {

    }
}