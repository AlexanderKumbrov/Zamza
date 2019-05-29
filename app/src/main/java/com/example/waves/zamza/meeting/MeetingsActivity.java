package com.example.waves.zamza.meeting;

import android.support.v4.app.Fragment;
import com.example.waves.zamza.CreateFragment;

public class MeetingsActivity extends CreateFragment {


    @Override
    public Fragment createFragment() {
        return new MeetingsFragment();
    }
}
