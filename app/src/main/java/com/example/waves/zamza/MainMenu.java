package com.example.waves.zamza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

import java.util.Set;


public class MainMenu extends AppCompatActivity {

    private ImageButton settings ;
    private Toolbar toolbar;
    SaveNightMode saveNightMode;

    protected void  onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        settings = (ImageButton)findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startSettings();
            }
        });
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
    public void startSettings (){
        Intent settings = new Intent(this ,SettingsActivity.class);
        startActivity(settings);
    }

    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}