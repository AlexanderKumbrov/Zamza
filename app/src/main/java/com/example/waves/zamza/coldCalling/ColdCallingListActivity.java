package com.example.waves.zamza.coldCalling;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.example.waves.zamza.CreateFragment;

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
