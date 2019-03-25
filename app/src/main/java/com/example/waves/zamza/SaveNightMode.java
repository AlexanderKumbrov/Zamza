package com.example.waves.zamza;

import android.content.Context;
import android.content.SharedPreferences;

public  class SaveNightMode {
    SharedPreferences sharedPreferences;

    public SaveNightMode(Context context) {
        sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
    }

    public void savedNightState(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }
    public Boolean loadNightModeState (){
        Boolean state = sharedPreferences.getBoolean("NightMode", false);
        return state;
    }
}
