package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

public class ColdCallingActivity extends CreateFragment {

    private static final String EXTRA_CALLING_ID = "com.example.waves.zamza.callingId";
    private ViewPager mViewPager;
    private List <ColdCalling>mColdCalling;

    public static Intent newIntent (Context packageContext , UUID callingId){
        Intent intent= new Intent(packageContext , ColdCallingActivity.class);
        intent.putExtra(EXTRA_CALLING_ID , callingId);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        return new ColdCallingFragment();
    }
    //This is a temporary solution.

}