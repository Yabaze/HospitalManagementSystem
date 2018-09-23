package com.thomas.mirakle.hospitalmanagementsystem.activities.fragements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.thomas.mirakle.hospitalmanagementsystem.R;

public class DoctorSpecializationListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public DoctorSpecializationListFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.list_view,container,false);

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Specialization, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        Log.d("Mirakle", "onItemClick: "+getListView().getItemAtPosition(position));
        DoctorDetailsFragment doctorDetailsFragment=new DoctorDetailsFragment(getListView().getItemAtPosition(position).toString());
        FragmentManager fragmentManager=getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container_two,doctorDetailsFragment).commit();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
