package com.example.waves.zamza;

import android.support.v4.app.Fragment;

public class ColdCallingActivity extends CreateFragment implements ColdCallingFragment.Callbacks {

    @Override
    public Fragment createFragment() {
        return new ColdCallingFragment();
    }

    @Override
    public void onColdSelected(ColdCalling coldCalling) {

    }
}
