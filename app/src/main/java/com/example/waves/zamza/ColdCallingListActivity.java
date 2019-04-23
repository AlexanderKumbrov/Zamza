package com.example.waves.zamza;

import android.support.v4.app.Fragment;

public class ColdCallingListActivity extends CreateFragment  {

    @Override
    public Fragment createFragment() {
        return new ColdCallingListFragment();
    }


}
