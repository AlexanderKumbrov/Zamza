package com.example.waves.zamza.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.waves.zamza.database.DbSchema.TableContacts;
import com.example.waves.zamza.database.DbSchema.TableMeeting;

public class BaseHelper extends SQLiteOpenHelper {
private static final int VERSION = 5;
private static final String DATABASE_NAME = "zamzaBase.db";

    public BaseHelper (Context context){
    super(context , DATABASE_NAME , null , VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableContacts.NAME+ "("+
        "_id integer primary key autoincrement,"
                + TableContacts.Cols.UUID + ", "
                + TableContacts.Cols.NAME + ", "
                + TableContacts.Cols.NUMBER + ","
                + TableContacts.Cols.POSITION + " ,"
                + TableContacts.Cols.COMPANY  + " ,"
                + TableContacts.Cols.CALL_COMPLETED +","
                + TableContacts.Cols.MAIL+" ,"
                + TableContacts.Cols.CONTACT_ID+","
                + TableContacts.Cols.RESULT_CALL+
                ")");

        db.execSQL("create table "+TableMeeting.NAME+ "("+
                "_id integer primary key autoincrement,"
                +  TableMeeting.Cols.UUID_MEETING +","
                        + TableMeeting.Cols.NAME_COMPANY +","
                        + TableMeeting.Cols.DATE +","
                        + TableMeeting.Cols.PLACE_MEETING +")"
                );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableContacts.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TableMeeting.NAME);
    }
}
