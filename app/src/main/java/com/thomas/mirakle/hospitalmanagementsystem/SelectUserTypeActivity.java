package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectUserTypeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton patientLogin,doctorLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        patientLogin=findViewById(R.id.patient_login);
        doctorLogin=findViewById(R.id.doctor_login);

        patientLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.patient_login:
                startActivity(new Intent(SelectUserTypeActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.doctor_login:
                break;
        }
    }
}
