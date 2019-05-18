package com.example.waves.zamza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.waves.zamza.database.BaseHelper;
import com.example.waves.zamza.database.CursorWrapperContacts;
import com.example.waves.zamza.database.CursorWrapperMeeting;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.waves.zamza.database.DbSchema.*;

public class ColdCallingLab {
    private static ColdCallingLab coldCallingLab;
    private Context mContext ;
    private SQLiteDatabase mDataBase;

   public static ColdCallingLab get(Context context){
        if (coldCallingLab == null){
            coldCallingLab = new ColdCallingLab(context);
        }
        return coldCallingLab;
    }
    ColdCallingLab(Context context){

        mContext = context.getApplicationContext();
        mDataBase = new BaseHelper(mContext).getWritableDatabase();
    }

    public void addContact(ColdCalling calling){
        ContentValues values = getContentValues(calling);
        mDataBase.insert(TableContacts.NAME , null , values);
    }
    public void addMeeting (Meeting meeting){
       ContentValues values = getContentValuesMeeting(meeting);
       mDataBase.insert(TableMeeting.NAME , null, values);
    }

    public List<ColdCalling> getColdCalling(){
        List <ColdCalling> coldCallings = new ArrayList<>();

        CursorWrapperContacts cursor  = queryNumber(null , null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                coldCallings.add(cursor.getColdCalling());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return coldCallings;
    }

public List<Meeting> getMeeting(){
       List<Meeting>meetings = new ArrayList<>();

       CursorWrapperMeeting cursorWrapperMeeting = queryMeeting(null, null);
       try {
           cursorWrapperMeeting.moveToFirst();
           while (!cursorWrapperMeeting.isAfterLast()) {
               meetings.add(cursorWrapperMeeting.getMeeting());
               cursorWrapperMeeting.moveToNext();
           }
       }finally {
           cursorWrapperMeeting.close();
       }
       return meetings;
}


public Meeting getMeeting (UUID idMeeting){
       CursorWrapperMeeting cursorWrapperMeeting = queryMeeting(TableMeeting.Cols.UUID_MEETING + " =?",new String[]{idMeeting.toString()});
       try {
           if (cursorWrapperMeeting.getCount() == 0){
               return null;
           }
           cursorWrapperMeeting.moveToFirst();
           return cursorWrapperMeeting.getMeeting();
       }finally {
           cursorWrapperMeeting.close();
       }
}

    public ColdCalling getColdCalling (UUID idCold){
        CursorWrapperContacts cursor = queryNumber(TableContacts.Cols.UUID + " =?" ,
                new String[]{idCold.toString()});
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getColdCalling();
        }
        finally {
            cursor.close();
        }
    }

    public void updateNumber (ColdCalling coldCalling){
        String uuidString  = coldCalling.getUuidCalling().toString();
        ContentValues values = getContentValues(coldCalling);

        mDataBase.update(TableContacts.NAME , values , TableContacts.Cols.UUID + " =? "
                , new String[]{uuidString});
    }


    public void deleteContact(ColdCalling coldCalling){
mDataBase.delete(TableContacts.NAME , TableContacts.Cols.UUID + " =?" , new String[]{coldCalling.getUuidCalling().toString()});
    }


    private static ContentValues getContentValues (ColdCalling coldCalling){
        ContentValues values = new ContentValues();
        values.put(TableContacts.Cols.UUID , coldCalling.getUuidCalling().toString());
        values.put(TableContacts.Cols.NAME, coldCalling.getNameCalling());
        values.put(TableContacts.Cols.NUMBER ,(coldCalling.getNumberCalling()));
        values.put(TableContacts.Cols.POSITION , coldCalling.getPositionCalling());
        values.put(TableContacts.Cols.COMPANY , coldCalling.getCompanyCalling());
        values.put(TableContacts.Cols.MAIL , coldCalling.getMailCalling());
        values.put(TableContacts.Cols.CONTACT_ID , coldCalling.getContactId());
        values.put(TableContacts.Cols.CALL_COMPLETED ,coldCalling.isCallComplete()? 1:0);
        values.put(TableContacts.Cols.RESULT_CALL , coldCalling.isResultCall() ? 1:0);
        return values;
    }
    private static ContentValues getContentValuesMeeting (Meeting meeting){
       ContentValues values = new ContentValues();
       values.put(TableMeeting.Cols.UUID_MEETING , meeting.getUuidMeeting().toString());
       values.put(TableMeeting.Cols.NAME_COMPANY , meeting.getNameCompanyMeeting());
       values.put(TableMeeting.Cols.PLACE_MEETING , meeting.getPlaceMeeting());
       return values;
    }
    private CursorWrapperContacts queryNumber (String whereClause , String[] whereArgs){
        Cursor cursor = mDataBase.query(
                TableContacts.NAME ,
                null ,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new CursorWrapperContacts(cursor);
    }
    private CursorWrapperMeeting queryMeeting (String whereClause , String[]whereArgs){
       Cursor cursor = mDataBase.query(
               TableMeeting.NAME,
               null,whereClause,
               whereArgs,
               null,
               null,
               null
       );
       return new CursorWrapperMeeting(cursor);
    }

}
