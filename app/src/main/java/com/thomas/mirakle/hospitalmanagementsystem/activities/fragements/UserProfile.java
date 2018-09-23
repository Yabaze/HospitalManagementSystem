package com.thomas.mirakle.hospitalmanagementsystem.activities.fragements;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thomas.mirakle.hospitalmanagementsystem.DBHandler;
import com.thomas.mirakle.hospitalmanagementsystem.Patient;
import com.thomas.mirakle.hospitalmanagementsystem.R;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserProfile extends Fragment implements View.OnClickListener {

    ImageView userProfileImage;
    ImageButton userProfileEdit;
    TextView userProfileName,userProfileGender,userProfileEmail,userProfileContact;
    Patient patientDetails;
    DBHandler database;
    View profileView;
    EditText firstName,lastName,email,mobileNumber;
    Spinner gender;

    String userName;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    public UserProfile() {
    }

    @SuppressLint("ValidFragment")
    public UserProfile(String email) {
        userName=email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileView=inflater.inflate(R.layout.user_profile,container,false);

        database=new DBHandler(profileView.getContext());

        init();

        userProfileEdit.setOnClickListener(this);

        return profileView;
    }

    @Override
    public void onClick(View view) {
        builder = new AlertDialog.Builder(profileView.getContext());
        builder.setTitle(getString(R.string.update_profile))
                .setView(R.layout.update_profile)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateProfile();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setIcon(R.mipmap.ic_launcher_background);

        alertDialog = builder.create();
        alertDialog.show();

        setDataToFields();
    }

    public void init(){
        patientDetails=database.retrieve(userName);

        userProfileImage=profileView.findViewById(R.id.user_profile_image);
        userProfileName=profileView.findViewById(R.id.user_profile_name);
        userProfileGender=profileView.findViewById(R.id.user_profile_gender);
        userProfileEmail=profileView.findViewById(R.id.user_profile_email);
        userProfileContact=profileView.findViewById(R.id.user_profile_contact);
        userProfileEdit=profileView.findViewById(R.id.user_profile_edit);

        if(Objects.equals(patientDetails.getGender().trim().toLowerCase(),"male")) {
            userProfileImage.setImageResource(R.drawable.user_profile_image_male);
        }
        else{
            userProfileImage.setImageResource(R.drawable.user_profile_image_female);
        }

        userProfileName.setText(String.format("%s %s",patientDetails.getFirstName(),patientDetails.getLastName()).toUpperCase());
        userProfileGender.setText(patientDetails.getGender());
        userProfileEmail.setText(patientDetails.getEmail());
        userProfileContact.setText(patientDetails.getMobileNumber());

    }

    public void updateProfile(){

        String nameFirst=firstName.getText().toString();
        String nameLast=lastName.getText().toString();
        String genderSelection=gender.getSelectedItem().toString();
        String mobile=mobileNumber.getText().toString();
        String eMail=email.getText().toString();

        Pattern mobilePattern=Pattern.compile("[6-9][0-9]{9}");

        if(nameFirst.trim().isEmpty()){
            firstName.setError("Enter Name ");
            firstName.requestFocus();
        }

        else if(mobile.trim().isEmpty()){
            mobileNumber.setError("Enter Phone Number");
            mobileNumber.requestFocus();
        }
        else {
            Matcher mobileMatcher=mobilePattern.matcher(mobile);
            if(mobileMatcher.find()){
                database.updateProfile(new Patient(nameFirst,nameLast,genderSelection,mobile,eMail));
            }
            else{
                mobileNumber.requestFocus();
                mobileNumber.setError("Enter Valid Mobile Number");
            }
        }
    }

    public void setDataToFields(){

        firstName=alertDialog.findViewById(R.id.firstName);
        lastName=alertDialog.findViewById(R.id.lastName);
        email=alertDialog.findViewById(R.id.email);
        mobileNumber=alertDialog.findViewById(R.id.mobileNumber);
        gender=alertDialog.findViewById(R.id.genderSpinner);

        firstName.setText(patientDetails.getFirstName());
        lastName.setText(patientDetails.getLastName());
        int selection;
        switch(patientDetails.getGender().toLowerCase()){
            case "male":
                selection=0;
                break;
            case "female":
                selection=1;
                break;
            default:
                selection=2;
                break;
        }
        gender.setSelection(selection);
        gender.setEnabled(false);
        mobileNumber.setText(patientDetails.getMobileNumber());
        email.setText(patientDetails.getEmail());
        email.setEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
