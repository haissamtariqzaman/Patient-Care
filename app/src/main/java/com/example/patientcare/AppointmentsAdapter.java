package com.example.patientcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder> {

    List<Appointment> appointments;
    AppointmentClickListener appointmentClickListener;

    public AppointmentsAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @NotNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_item, parent, false);
        AppointmentViewHolder appointmentViewHolder = new AppointmentViewHolder(view);
        return appointmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppointmentsAdapter.AppointmentViewHolder holder, int position) {
        holder.bind(appointments.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentClickListener.onAppointmentClick(appointments.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView patient_name, date, time, room;
        public AppointmentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            patient_name = itemView.findViewById(R.id.patient_name);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            room = itemView.findViewById(R.id.room);
        }

        public void bind(Appointment appointment) {
            patient_name.setText(appointment.getPatient_name());
            date.setText(appointment.getDate());
            time.setText(appointment.getTime());
            room.setText(appointment.getRoom());
        }
    }

    interface AppointmentClickListener {
        public void onAppointmentClick(Appointment appointment);
    }
}
