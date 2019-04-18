package com.example.waves.zamza;

import android.support.v4.app.Fragment;

public class ColdCallingListActivity extends CreateFragment implements ColdCallingListFragment.Callbacks {

    @Override
    public Fragment createFragment() {
        return new ColdCallingListFragment();
    }

    @Override
    public void onColdSelected(ColdCalling coldCalling) {

    }
}
