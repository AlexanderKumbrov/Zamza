package com.example.waves.zamza;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ColdCallingLab {
    private static ColdCallingLab coldCallingLab;
    private List <ColdCalling>mColdCalling;

    static ColdCallingLab get(Context context){
        if (coldCallingLab == null){
            coldCallingLab = new ColdCallingLab(context);
        }
        return coldCallingLab;
    }
    ColdCallingLab(Context context){
        mColdCalling = new ArrayList<>();

        for (int i =0 ;i <10 ;i++){
            ColdCalling coldCalling = new ColdCalling();
            coldCalling.setNameCalling("Клиент №"+i);
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
