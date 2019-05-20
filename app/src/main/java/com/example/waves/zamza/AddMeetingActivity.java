package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;
import java.util.UUID;


public class AddMeetingActivity extends CreateFragment {

    private List<Meeting> mMeetings;

    private static final String EXTRA_MEET_ID ="com.example.waves.zamza.id";
    public static Intent newIntent (Context context , UUID meetingId){
        Intent intent = new Intent(context , AddMeetingActivity.class);
        intent.putExtra(EXTRA_MEET_ID, meetingId);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        UUID callId = (UUID)getIntent().getSerializableExtra(EXTRA_MEET_ID);;
        return new AddMeetingFragment().newInstance(callId);
    }
}



