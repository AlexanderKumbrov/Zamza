package com.example.waves.zamza;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;


public class MainMenu extends AppCompatActivity {

    private FloatingActionButton floatSwitch;
    private boolean flagNightMode;
    private SaveNightMode saveNightMode;

    protected void  onCreate (Bundle savedInstanceState){

        saveNightMode = new SaveNightMode(this);
        if (saveNightMode.loadNightModeState() ==true){
            setTheme(R.style.NightMode);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (saveNightMode.loadNightModeState() == true){
            flagNightMode = !flagNightMode;
        }
        floatSwitch = (FloatingActionButton) findViewById(R.id.night_mode);
        floatSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagNightMode = !flagNightMode;
                if (flagNightMode){
                    saveNightMode.savedNightState(true);
                    appRestart();
                    flagNightMode = false;
                }
                else {
                    saveNightMode.savedNightState(false);
                    appRestart();
                    flagNightMode = true;
                }
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu menu ){
        getMenuInflater().inflate(R.menu.menu_main_tool_bar , menu);
return true;
    }
    public void cold_calling (View view){
        Intent cold = new Intent(this , ColdCallingActivity.class);
        startActivity(cold);
    }
    public void debt_repayment_term(View view){
        Intent debt = new Intent(this ,DebtRepaymentTermActivity.class);
        startActivity(debt);
    }
    public void meetings(View view){
        Intent meetings = new Intent(this ,MeetingsActivity.class);
        startActivity(meetings);
    }
    public void sales_training(View view){
        Intent sales = new Intent(this ,SalesTraining.class);
        startActivity(sales);
    }

    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void appRestart(){
        Intent restart = new Intent(getApplicationContext(),MainMenu.class);
        startActivity(restart);
        finish();
    }
}