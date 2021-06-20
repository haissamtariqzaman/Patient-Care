package com.example.patientcare;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private ArrayList<Appointment> appointments;
    //private ArrayList<Doctor> doctors;

    public AppointmentAdapter(ArrayList<Appointment> a)
    {
        appointments=a;
        //doctors=d;
    }

    public void updateAppointments(ArrayList<Appointment> a)
    {
        appointments=a;
        notifyDataSetChanged();
    }

    /*public void updateDoctors(ArrayList<Doctor> d)
    {
        doctors=d;
        notifyDataSetChanged();
    }*/

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_item_apointment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppointmentAdapter.ViewHolder holder, int position) {
        Appointment a=appointments.get(position);

        holder.dr_name.setText(a.d.getName());
        holder.dr_speciality.setText(a.d.getSpeciality());
        holder.room_no.setText("Room No: "+a.getRoom());
        holder.Date.setText(a.getDate());
        holder.time.setText(a.getTime());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dr_name;
        public TextView dr_speciality;
        public TextView room_no;
        public TextView Date;
        public TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dr_name=itemView.findViewById(R.id.dr_name);
            dr_speciality=itemView.findViewById(R.id.dr_speciality);
            room_no=itemView.findViewById(R.id.room_no);
            Date=itemView.findViewById(R.id.Date);
            time=itemView.findViewById(R.id.time);
        }

    }
}