package com.thomas.mirakle.hospitalmanagementsystem.activities.fragements;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.mirakle.hospitalmanagementsystem.R;
import com.thomas.mirakle.hospitalmanagementsystem.activities.adaptor.Doctor;
import com.thomas.mirakle.hospitalmanagementsystem.activities.adaptor.DoctorCardViewAdapter;

import java.util.ArrayList;

public class DoctorDetailsFragment extends Fragment {
    //String[] strings = {"1", "2", "3", "4", "5", "6", "7"};

    ArrayList<Doctor> doctorArrayList;

    String specialization;

    RecyclerView recycleView;

    public DoctorDetailsFragment() {
        this.specialization="ENT";
        createArrayList();
    }

    @SuppressLint("ValidFragment")
    public DoctorDetailsFragment(String specialization) {
        this.specialization = specialization;
        createArrayList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View recyclerView=inflater.inflate(R.layout.)

        View recyclerView=inflater.inflate(R.layout.recycler_list_view,container,false);

        recycleView =  recyclerView.findViewById(R.id.recycler_list_view);


        /*RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new DoctorCardViewAdapter(doctorArrayList));
        */

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        Log.d("debugMode", "The application stopped after this");
        recycleView.setLayoutManager(mLayoutManager);

        DoctorCardViewAdapter doctorCardViewAdapter=new DoctorCardViewAdapter(doctorArrayList);

        recycleView.setAdapter(doctorCardViewAdapter);

        return recyclerView;
    }

    public void createArrayList(){
        doctorArrayList=new ArrayList<Doctor>();
        doctorArrayList.add(new Doctor("Thomas","9442428244",specialization,"SAT & SUN"));
        doctorArrayList.add(new Doctor("Anbu","9486953064",specialization,"MON - FRI"));
    }
}
