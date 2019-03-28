package com.example.waves.zamza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
private Switch nightMode;
private SaveNightMode saveNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nightMode = (Switch)findViewById(R.id.switch_night_mode);


    }


    }

