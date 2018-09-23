package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBHandler extends DatabaseHelper {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context);
        dbHelper = new DatabaseHelper(context);
    }

    public long insertData(Patient patient) {

        db = dbHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(COLUMN_NAME_FIRST_NAME, patient.getFirstName());
        data.put(COLUMN_NAME_LAST_NAME, patient.getLastName());
        data.put(COLUMN_NAME_GENDER, patient.getGender());
        data.put(COLUMN_NAME_PHONE_NUMBER, patient.getMobileNumber());
        data.put(COLUMN_NAME_E_MAIL, patient.getEmail());
        data.put(COLUMN_NAME_PASSWORD, patient.getPassword());
        long success = db.insert(TABLE_NAME, null, data);
        db.close();

        return success;
    }

    public int updatePassword(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PASSWORD, patient.getPassword());

        String selection = COLUMN_NAME_E_MAIL + " LIKE ?";
        String[] selectionArgs = {patient.getEmail()};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public boolean checkCredential(String username,String password) {
        boolean isSuccess=false;
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_NAME_E_MAIL,
                COLUMN_NAME_PASSWORD
        };
        String selection = COLUMN_NAME_E_MAIL + "=? and "+COLUMN_NAME_PASSWORD+"=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

       if (cursor.getCount()>0){
            Log.d(TAG, "checkCredential: Success");
            isSuccess=true;
        }else{
            Log.d(TAG, "checkCredential: Failed");
        }
        cursor.close();
        db.close();
        return isSuccess;

    }

    public Patient retrieve(String username) {
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_NAME_FIRST_NAME,
                COLUMN_NAME_LAST_NAME,
                COLUMN_NAME_GENDER,
                COLUMN_NAME_PHONE_NUMBER,
                COLUMN_NAME_E_MAIL
        };

        String selection = COLUMN_NAME_E_MAIL + " = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        cursor.moveToFirst();

        Patient patientData=new Patient(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        Log.d(TAG, "retrieve: "+patientData);


        cursor.close();
        return patientData;
    }

    public int updateProfile(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_FIRST_NAME,patient.getFirstName());
        values.put(COLUMN_NAME_LAST_NAME,patient.getLastName());
        values.put(COLUMN_NAME_PHONE_NUMBER,patient.getMobileNumber());

        String selection = COLUMN_NAME_E_MAIL + " LIKE ?";
        String[] selectionArgs = {patient.getEmail()};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }
}
