package com.example.waves.zamza;

import android.content.Context;

import java.util.List;

public class ColdCallingLab {
    private static ColdCallingLab coldCallingLab;
    private List <ColdCalling>mColdCalling;

    static ColdCallingLab get(Context context){
        if (coldCallingLab == null){
            coldCallingLab = new ColdCallingLab();
        }
        return coldCallingLab;
    }
}
