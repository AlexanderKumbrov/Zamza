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
saveNightMode = new SaveNightMode(this);
if (saveNightMode.loadNightModeState() ==true){
setTheme(R.style.NightMode);
}else {
    setTheme(R.style.AppTheme);
}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nightMode = (Switch)findViewById(R.id.switch_night_mode);
        if (saveNightMode.loadNightModeState() == true){
            nightMode.setChecked(true);
        }
        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (isChecked){
    saveNightMode.savedNightState(true);
    appRestart();
}
else
saveNightMode.savedNightState(false);
appRestart();
            }
        });
    }

    public void appRestart(){
        Intent restart = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(restart);
        finish();
    }
}
