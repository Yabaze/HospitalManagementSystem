package com.thomas.mirakle.hospitalmanagementsystem.activities.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.thomas.mirakle.hospitalmanagementsystem.R;

public class DoctorCardViewHolder extends RecyclerView.ViewHolder {

    EditText doctorName,doctorSpecialization,doctorContact,doctorAvailability;

    public DoctorCardViewHolder(@NonNull View itemView) {
        super(itemView);
        doctorName=itemView.findViewById(R.id.doctor_name);
        doctorSpecialization=itemView.findViewById(R.id.doctor_specialization);
        doctorContact=itemView.findViewById(R.id.doctor_contact);
        doctorAvailability=itemView.findViewById(R.id.doctor_availability);

        //doctorName= (EditText) itemView;
        ///doctorSpecialization= (EditText) itemView;
        //doctorContact=(EditText)itemView;
        //doctorAvailability=(EditText)itemView;
    }
}
