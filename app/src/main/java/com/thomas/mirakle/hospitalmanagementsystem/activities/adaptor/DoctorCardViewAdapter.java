package com.thomas.mirakle.hospitalmanagementsystem.activities.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.thomas.mirakle.hospitalmanagementsystem.R;

import java.util.ArrayList;

public class DoctorCardViewAdapter extends RecyclerView.Adapter<DoctorCardViewHolder> {
    private ArrayList<Doctor> doctorArrayList;

    EditText doctorName,doctorSpecialization,doctorAvailability,doctorContact;

    public DoctorCardViewAdapter(ArrayList<Doctor> doctorArrayList) {
        this.doctorArrayList = doctorArrayList;
    }

    @NonNull
    @Override
    public DoctorCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View doctorProfileView=View.inflate(viewGroup.getContext(), R.layout.doctor_profile,viewGroup);


        View view = new RecyclerView(viewGroup.getContext());


        doctorName=doctorProfileView.findViewById(R.id.doctor_name);
        doctorSpecialization=doctorProfileView.findViewById(R.id.doctor_specialization);
        doctorContact=doctorProfileView.findViewById(R.id.doctor_contact);
        doctorAvailability=doctorProfileView.findViewById(R.id.doctor_availability);



        DoctorCardViewHolder viewHolder = new DoctorCardViewHolder(view);//view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorCardViewHolder doctorCardViewHolder, int i) {

        doctorName.setText(doctorArrayList.get(i).getDoctorName());
        doctorSpecialization.setText("ENT");//doctorArrayList.get(i).getDoctorSpecialization());
        doctorAvailability.setText("7PM");//doctorArrayList.get(i).getDoctorAvailability());
        doctorContact.setText("94442");//doctorArrayList.get(i).getDoctorContactNumber());

        //doctorCardViewHolder//textView.setText(dataSource[i]);

    }

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }
}
