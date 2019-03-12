package com.example.waves.zamza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.waves.zamza.database.BaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ColdCallingLab {
    private static ColdCallingLab coldCallingLab;
    private List <ColdCalling>mColdCalling;
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
        mColdCalling = new ArrayList<>();

        for (int i =0 ;i <20 ;i++){
            ColdCalling coldCalling = new ColdCalling();
            coldCalling.setNameCalling("Клиент "+i);
            mColdCalling.add(coldCalling);
        }
    }
    public List<ColdCalling>getmColdCalling(){
        return mColdCalling;
    }
    public ColdCalling getColdCalling (UUID idCold){
        for (ColdCalling coldCalling : mColdCalling){
            if (coldCalling.getUuidCalling().equals(idCold)){
                return coldCalling;
            }
        }
        return null;
    }
}
