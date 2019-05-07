package com.example.waves.zamza.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.waves.zamza.database.DbSchema.Table;

public class BaseHelper extends SQLiteOpenHelper {
private static final int VERSION = 2;
private static final String DATABASE_NAME = "zamzaBase.db";

    public BaseHelper (Context context){
    super(context , DATABASE_NAME , null , VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table.NAME+ "("+
        "_id integer primary key autoincrement,"
                +Table.Cols.UUID + ", "
                +Table.Cols.NAME + ", "
                +Table.Cols.NUMBER + ","
                +Table.Cols.POSITION + " ,"
                +Table.Cols.COMPANY  + " ,"
                +Table.Cols.CALL_COMPLETED +","
                +Table.Cols.MAIL+" ,"
                +Table.Cols.CONTACT_ID+","
                +Table.Cols.RESULT_CALL+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
