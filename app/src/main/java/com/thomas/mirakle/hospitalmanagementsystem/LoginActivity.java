package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thomas.mirakle.hospitalmanagementsystem.activities.DashBoard;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    TextInputLayout usernameLayout,passwordLayout;
    Button loginButton,registerButton;
    DBHandler database;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    public void init(){
        database=new DBHandler(this);
        mContext=LoginActivity.this;

        sharedPreferences = mContext.getSharedPreferences(getString(R.string.sharedPreferenceKey), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        usernameLayout=findViewById(R.id.username_layout);
        passwordLayout=findViewById(R.id.password_layout);

        loginButton=findViewById(R.id.btn_login);
        registerButton=findViewById(R.id.btn_register);
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                loginPatient();
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
        }
    }

    private void loginPatient(){
        String username,password;
        username=this.username.getText().toString();
        password=this.password.getText().toString();
        if(username.isEmpty()||username==""){
            this.username.setError("Wrong Credential");
        }
        else if(password.isEmpty()||password==""){
            this.password.setError("Wrong Credential");
        }
        else {
            boolean isSuccess = database.checkCredential(username, password);
            if(isSuccess){
                Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(LoginActivity.this,DashBoard.class);
                intent.putExtra("username",username);

                editor.putString(getString(R.string.email),username);
                editor.putString(getString(R.string.password), password);
                editor.commit();

                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this,"Wrong Username and Password.",Toast.LENGTH_LONG).show();
            }
        }
    }
}



