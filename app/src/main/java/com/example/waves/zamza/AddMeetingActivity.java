package com.example.waves.zamza;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddMeetingActivity extends CreateFragment {

    @Override
    public Fragment createFragment() {
        return new AddMeetingFragment();
    }

}
