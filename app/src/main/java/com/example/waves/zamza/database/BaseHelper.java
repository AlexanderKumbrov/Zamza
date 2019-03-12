package com.example.waves.zamza.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseHelper extends SQLiteOpenHelper {
private static final int VERSION = 1;
private static final String DATABASE_NAME = "zamzaBase.db";

    public BaseHelper (Context context){
    super(context , DATABASE_NAME , null , VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
