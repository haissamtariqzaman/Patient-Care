package com.example.patientcare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.parseInt;

public class SignupService extends Service {

    private Doctor doc;
    private String date;
    private DAODoctor daoDoctor;

    public void onCreate(){
        daoDoctor=new DAODoctor();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doc=(Doctor)intent.getSerializableExtra("doctor");
        date=(String)intent.getSerializableExtra("date");
        register();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void register()
    {
        int dt=0,month=0,year=0;

        StringBuilder sb=new StringBuilder();

        for(int x=0;x<date.length();x++) {
            if (date.charAt(x) == '/') {
                if (dt == 0) {
                    dt = parseInt(sb.toString());
                    sb = new StringBuilder();
                } else {
                    month = parseInt(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb = sb.append(date.charAt(x));
            }
        }
        year=parseInt(sb.toString());

        doc.setDate(dt);
        doc.setMonth(month);
        doc.setYear(year);

        daoDoctor.addDoctor(doc).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    daoDoctor.addDoctorData(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(SignupService.this,"Signup Complete!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(SignupService.this,"Signup Failed!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}