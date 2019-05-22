package com.example.waves.zamza;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class ColdCallingListActivity extends CreateFragment implements ColdCallingListFragment.Callbacks  {

    @Override
    public Fragment createFragment() {
        return new ColdCallingListFragment();
    }


    @Override
    public void onCallSelected(ColdCalling coldCalling) {
        Intent intent = ColdCallingActivity.newIntent(this, coldCalling.getUuidCalling());
        startActivity(intent);
    }
}
