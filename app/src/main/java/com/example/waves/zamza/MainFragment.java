package com.example.waves.zamza;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainFragment extends AppCompatActivity {

    protected void  onCreate (Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_menu);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 }
 public void cold_calling (View view){
     Intent cold = new Intent(this , ColdCallingActivity.class);
     startActivity(cold);
 }
}
