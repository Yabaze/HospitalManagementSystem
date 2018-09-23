package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.thomas.mirakle.hospitalmanagementsystem.activities.DashBoard;
import com.thomas.mirakle.hospitalmanagementsystem.sharedData.SharedPreferenceData;

public class SplashScreen extends AppCompatActivity {

    Context mContext;
    SharedPreferenceData sharedData;
    DBHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        },2000);

    }

    private void init(){
        mContext=getApplicationContext();

        sharedData=new SharedPreferenceData(mContext);

        database=new DBHandler(mContext);

        boolean isSuccess = database.checkCredential(sharedData.getUsername(), sharedData.getPassword());
        if(isSuccess){
            Intent intent=new Intent(mContext,DashBoard.class);
            intent.putExtra(getString(R.string.intent_extra_data),sharedData.getUsername());
            startActivity(intent);
            finish();
        }
        else{
            Intent intent=new Intent(mContext,SelectUserTypeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
