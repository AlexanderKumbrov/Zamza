package com.example.waves.zamza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.waves.zamza.database.BaseHelper;
import com.example.waves.zamza.database.CursorWrapperZamza;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.waves.zamza.database.DbSchema.*;

public class ColdCallingLab {
    private static ColdCallingLab coldCallingLab;

    private Context mContext ;
    private SQLiteDatabase mDataBase;

    static ColdCallingLab get(Context context){
        if (coldCallingLab == null){
            coldCallingLab = new ColdCallingLab(context);
        }
        return coldCallingLab;
    }
    ColdCallingLab(Context context){
        mContext = context.getApplicationContext();
        mDataBase = new BaseHelper(mContext).getWritableDatabase();

        for (int i =0 ;i <20 ;i++){
            ColdCalling coldCalling = new ColdCalling();
            coldCalling.setNameCalling("Клиент "+i);

        }
    }
    public List<ColdCalling>getmColdCalling(){
        List <ColdCalling>coldCallings = new ArrayList<>();

        CursorWrapperZamza cursor  = queryNumber(null , null);
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
    public void addNumber (ColdCalling calling){
        ContentValues values = getContentValues(calling);
        mDataBase.insert(Table.NAME , null , values);
    }
    public void updateNumber (ColdCalling coldCalling){
        String uuidString  = coldCalling.getUuidCalling().toString();
        ContentValues values = getContentValues(coldCalling);

        mDataBase.update(Table.NAME , values , Table.Cols.UUID + " ?" , new String[]{uuidString});
    }
    private static ContentValues getContentValues (ColdCalling coldCalling){
        ContentValues values = new ContentValues();
        values.put(Table.Cols.UUID , coldCalling.getUuidCalling().toString());
        values.put(Table.Cols.NAME, coldCalling.getNameCalling());
        values.put(Table.Cols.NUMBER , coldCalling.getNumberCalling());
        values.put(Table.Cols.POSITION , coldCalling.getPositionCalling());
        values.put(Table.Cols.COMPANY , coldCalling.getCompanyCalling());
        values.put(Table.Cols.MAIL , coldCalling.getMailCalling());
        return values;
    }
    private CursorWrapperZamza queryNumber (String whereClause , String[] whereArgs){
        Cursor cursor = mDataBase.query(Table.NAME ,
                null ,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new CursorWrapperZamza(cursor);
    }
    public ColdCalling getColdCalling (UUID idCold){
        CursorWrapperZamza cursor = queryNumber(Table.Cols.UUID + " ?" ,
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
}
