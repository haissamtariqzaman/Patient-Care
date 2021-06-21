package com.example.patientcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Prescription_Adapter extends RecyclerView.Adapter<Prescription_Adapter.ViewHolder> {

    ArrayList<Appointment> app;
    Context c;
    private onClickListener onClickListener;

    public Prescription_Adapter(ArrayList<Appointment> a, Context c_, onClickListener listener)
    {
        app=a;
        c=c_;
        onClickListener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_recycler_item,parent,false);
        return new ViewHolder(view,onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Prescription_Adapter.ViewHolder holder, int position) {
        Appointment a=app.get(position);
        holder.docName.setText("Appointment with "+a.d.getName());
        holder.date.setText("Date: "+a.getDate());
        holder.time.setText("Time: "+a.getTime());
    }

    @Override
    public int getItemCount() {
        return app.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView docName;
        public TextView date;
        public  TextView time;
        public onClickListener onClickListener;

        public ViewHolder(@NonNull View itemView,onClickListener listener) {
            super(itemView);
            docName=itemView.findViewById(R.id.textView15);
            date=itemView.findViewById(R.id.textView20);
            time=itemView.findViewById(R.id.textView25);
            onClickListener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onPrescriptionClicked(getAdapterPosition());
        }
    }

    public interface onClickListener{
        void onPrescriptionClicked(int index);
    }

    public void updateAppointments(ArrayList<Appointment> a)
    {
        app=a;
        notifyDataSetChanged();
    }
}