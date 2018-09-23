package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class SetNewPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    Button updatePasswordButton;
    DBHandler database;
    String id;
    EditText passwordOne,passwordTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        init();
    }

    public void init(){
        Bundle data=getIntent().getExtras();
        id=data.getString("username");

        database=new DBHandler(this);

        updatePasswordButton=findViewById(R.id.updatePassword);
        updatePasswordButton.setOnClickListener(this);

        passwordOne=findViewById(R.id.passwordOne);
        passwordTwo=findViewById(R.id.passwordTwo);
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updatePassword:
                if(Objects.equals(passwordTwo.getText().toString(),"")) {
                    Toast.makeText(getApplicationContext(),"Try Again..",Toast.LENGTH_LONG).show();
                }
                else {
                    if (Objects.equals(passwordOne.getText().toString(), passwordTwo.getText().toString()))
                    {
                        int successUpdate = database.updatePassword(new Patient(id, passwordTwo.getText().toString()));
                        if (successUpdate > 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPasswordActivity.this);
                            builder.setTitle("Password Updated")
                                    .setMessage("Your Email is an Username for you.")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            startActivity(new Intent(SetNewPasswordActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    }).setIcon(R.mipmap.ic_launcher_background);

                            AlertDialog a = builder.create();
                            a.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Patient Creation Failed..", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SetNewPasswordActivity.this, RegisterActivity.class));
                            finish();
                        }
                    }
                    else{
                        passwordTwo.setError("Password MisMatch...");
                        passwordTwo.requestFocus();
                    }
                }
                break;
        }
    }
}
