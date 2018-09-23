package com.thomas.mirakle.hospitalmanagementsystem.sharedData;

import android.content.Context;
import android.content.SharedPreferences;

import com.thomas.mirakle.hospitalmanagementsystem.R;

public class SharedPreferenceData {
    private String username;
    private String password;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;
    String sharedPreferenceKeyValue;
    public SharedPreferenceData(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharedPreferenceKey),Context.MODE_PRIVATE);
        sharedPreferenceKeyValue = context.getResources().getString(R.string.sharedPreferenceKey);
        this.username = sharedPreferences.getString(context.getString(R.string.email), sharedPreferenceKeyValue);
        this.password = sharedPreferences.getString(context.getString(R.string.password),sharedPreferenceKeyValue);
        editor = sharedPreferences.edit();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        editor.putString(context.getString(R.string.email),username);
        editor.commit();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        editor.putString(context.getString(R.string.password), password);
        editor.commit();
    }
}
