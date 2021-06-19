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

    public AppointmentsAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @NotNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_item, parent, false);

        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppointmentsAdapter.AppointmentViewHolder holder, int position) {
        holder.bind(appointments.get(position));
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
            patient_name.setText(appointment.patient_name);
            date.setText(appointment.date);
            time.setText(appointment.time);
            room.setText(appointment.room);
        }
    }
}
