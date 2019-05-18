package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class AddMeetingActivity extends AppCompatActivity {

    private static final String EXTRA_CALL_ID ="com.example.waves.zamza.id";
    public static Intent newIntent (Context context , UUID meetingId){
        Intent intent = new Intent(context , AddMeetingActivity.class);
        intent.putExtra(EXTRA_CALL_ID , meetingId);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fragment_meeting);

}

}



