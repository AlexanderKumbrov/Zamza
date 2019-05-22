package com.example.waves.zamza;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.example.waves.zamza.AddMeetingFragment;
import com.example.waves.zamza.CreateFragment;
import com.example.waves.zamza.DateFragment;

import java.util.Date;

public class DateActivity extends CreateFragment {

    @Override
    public Fragment createFragment() {
        Date date = (Date)getIntent().getSerializableExtra(AddMeetingFragment.EXTRA_DATE);
        return DateFragment.newInstance(date);
    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode , resultCode , data);
    }
}
