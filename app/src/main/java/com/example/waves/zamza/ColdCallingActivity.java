package com.example.waves.zamza;

import android.support.v4.app.Fragment;

public class ColdCallingActivity extends CreateFragment {

    @Override
    public Fragment createFragment() {
        return new ColdCallingFragment();
    }
}
