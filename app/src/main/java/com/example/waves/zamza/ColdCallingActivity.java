package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

public class ColdCallingActivity extends AppCompatActivity {

    private static final String EXTRA_CALLING_ID = "com.example.waves.zamza.callingId";
    private ViewPager mViewPager;
    private List <ColdCalling>mColdCalling;

    public static Intent newIntent (Context packageContext , UUID callingId){
        Intent intent= new Intent(packageContext , ColdCallingActivity.class);
        intent.putExtra(EXTRA_CALLING_ID , callingId);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acriviry_call_pager);
UUID callId = (UUID)getIntent().getSerializableExtra(EXTRA_CALLING_ID);
mViewPager = (ViewPager)findViewById(R.id.activity_call_view_pager);
mColdCalling = ColdCallingLab.get(this).getmColdCalling();
        FragmentManager fragmentManager =getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                ColdCalling coldCalling = mColdCalling.get(i);
                return ColdCallingFragment.newInstance(coldCalling.getUuidCalling());
            }

            @Override
            public int getCount() {
                return mColdCalling.size();
            }
        });
        for (int i = 0 ; i <mColdCalling.size() ; i++){
            if (mColdCalling.get(i).getUuidCalling().equals(callId));
            mViewPager.setCurrentItem(i);
            break;
        }
    }


}