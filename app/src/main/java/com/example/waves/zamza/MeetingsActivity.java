package com.example.waves.zamza;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MeetingsActivity extends CreateFragment {


    @Override
    public Fragment createFragment() {
        return new MeetingsFragment();
    }
}
