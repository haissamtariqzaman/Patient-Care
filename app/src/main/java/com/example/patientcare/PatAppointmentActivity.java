package com.example.patientcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class PatAppointmentActivity extends AppCompatActivity {

    private TextView dr_name;
    private TextView dr_speciality;
    private TextView select_date;
    private Spinner spinner_time;
    private Button book_button;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private DAOAppointment daoAppointment;

    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_ui);

        Intent intent=getIntent();
        doctor=(Doctor) intent.getSerializableExtra("doctor");

        dr_name=findViewById(R.id.dr_name);
        dr_speciality=findViewById(R.id.dr_speciality);
        select_date=findViewById(R.id.select_date);
        spinner_time=findViewById(R.id.spinner_time);
        book_button=findViewById(R.id.book_button);

        dr_name.setText(doctor.getName());
        dr_speciality.setText(doctor.getSpeciality());

        daoAppointment=new DAOAppointment();

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateClicked();
            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                StringBuilder sb=new StringBuilder();
                sb.append(dayOfMonth).append("/").append(month).append("/").append(year);
                select_date.setText(sb.toString());

                updateTimeSlots();
            }
        };
    }

    public void selectDateClicked(){
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(PatAppointmentActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    public void updateTimeSlots()
    {
        daoAppointment.getDoctorId(doctor).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    String docID = null;
                    for(QueryDocumentSnapshot doc : task.getResult())
                    {
                        docID=doc.getId();
                    }

                    daoAppointment.getAppointments(docID,select_date.getText().toString()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful())
                            {
                                ArrayList<Appointment> appointments=new ArrayList<>();
                                for(QueryDocumentSnapshot doc : task.getResult())
                                {
                                    Appointment a=doc.toObject(Appointment.class);
                                    appointments.add(a);
                                }

                                ArrayList<String> timeSlots=generateTimeSlots(appointments);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PatAppointmentActivity.this, android.R.layout.simple_spinner_item, timeSlots);
                                spinner_time.setAdapter(adapter);
                            }
                        }
                    });

                }
            }
        });
    }

    public ArrayList<String> generateTimeSlots(ArrayList<Appointment> appointments)
    {
        ArrayList<String> times=new ArrayList<>();

        for(int hour=9,min=0;hour<=21 || min<15;min+=15)
        {
            if(min==60)
            {
                hour++;
                min=0;
            }

            StringBuilder sb=new StringBuilder();
            if(hour<10)
            {
                sb.append("0");
            }
            sb.append(hour).append(":");
            if(min<10)
            {
                sb.append("0");
            }
            sb.append(min);

            String time=sb.toString();

            boolean found=false;

            for(int x=0;x<appointments.size();x++)
            {
                if(appointments.get(x).time.equals(time))
                {
                    found=true;
                    break;
                }
            }

            if(!found)
            {
                times.add(time);
            }
        }

        return times;
    }
}