package com.thomas.mirakle.hospitalmanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Patient.db";
    public static final String TABLE_NAME = "patient";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_FIRST_NAME = "firstName";
    public static final String COLUMN_NAME_LAST_NAME = "lastname";
    public static final String COLUMN_NAME_GENDER = "gender";
    public static final String COLUMN_NAME_PHONE_NUMBER = "phone";
    public static final String COLUMN_NAME_E_MAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    private static final String CREATE_PATIENT_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_FIRST_NAME + " TEXT NOT NULL," +
                    COLUMN_NAME_LAST_NAME + " TEXT,"+
                    COLUMN_NAME_GENDER+" TEXT NOT NULL,"+
                    COLUMN_NAME_PHONE_NUMBER+" TEXT NOT NULL,"+
                    COLUMN_NAME_E_MAIL+" TEXT NOT NULL,"+
                    COLUMN_NAME_PASSWORD+" TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onUpgrade(sqLiteDatabase, DATABASE_VERSION, 2);
    }
}
