package com.example.patientcare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    ArrayList<Doctor> doctors;
    Context c;

    public DoctorAdapter(ArrayList<Doctor> d, Context c_)
    {
        doctors=d;
        c=c_;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_doctors,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorAdapter.ViewHolder holder, int position) {
        Doctor d=doctors.get(position);
        holder.docName.setText(d.getName());
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView docName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            docName=itemView.findViewById(R.id.textView17);
        }
    }
}
