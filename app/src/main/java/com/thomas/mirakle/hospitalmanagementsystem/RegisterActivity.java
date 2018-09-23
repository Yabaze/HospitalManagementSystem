package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText firstName,lastName,email,mobileNumber;
    Button register;
    Spinner gender;
    DBHandler database;
    private final String TAG="RegisterActivity.this";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database=new DBHandler(this);

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.email);
        mobileNumber=findViewById(R.id.mobileNumber);
        gender=findViewById(R.id.genderSpinner);
        register=findViewById(R.id.btn_register);

        register.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                    String nameFirst=firstName.getText().toString();
                    String nameLast=lastName.getText().toString();
                    String genderSelection=gender.getSelectedItem().toString();
                    String mobile=mobileNumber.getText().toString();
                    String eMail=email.getText().toString();
                    Long success= 0L;
                    Pattern mobilePattern=Pattern.compile("[6-9][0-9]{9}");
                    Pattern emailPattern=Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]{2,}");

                if(nameFirst.trim().isEmpty()){
                        firstName.setError("Enter Name ");
                        firstName.requestFocus();
                    }
                    else if(eMail.trim().isEmpty()){
                        email.setError("Enter Email Address..");
                        email.requestFocus();
                    }
                    else if(!emailPattern.matcher(eMail.trim()).find()){
                        email.setError("Enter Valid Email.");
                        email.requestFocus();
                    }

                    else if(mobile.trim().isEmpty()){
                        mobileNumber.setError("Enter Phone Number");
                        mobileNumber.requestFocus();
                    }
                    else {
                        Matcher mobileMatcher=mobilePattern.matcher(mobile);
                        if(mobileMatcher.find()){
                            success=database.insertData(new Patient(nameFirst,nameLast,genderSelection,mobile,eMail));
                            if(success>0){
                                Log.d(TAG, "onClick: Success"+success);
                                Intent intent=new Intent(RegisterActivity.this,SetNewPasswordActivity.class);
                                intent.putExtra("username",eMail);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                reset();
                            }
                        }
                        else{
                            mobileNumber.requestFocus();
                            mobileNumber.setError("Enter Valid Mobile Number");
                        }
                    }

                break;
        }
    }

    private void reset(){
        firstName.setText("");
        lastName.setText("");
        gender.setSelection(0);
        email.setText("");
        mobileNumber.setText("");
    }
}
