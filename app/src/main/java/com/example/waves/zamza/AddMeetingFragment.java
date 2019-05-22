package com.example.waves.zamza;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class AddMeetingFragment extends Fragment {

    private EditText nameMeeting;
    private EditText placeMeeting;
    private Meeting mMeeting;
    private FloatingActionButton mapButton;
    private Button dateMeetingButton;

    private static final String ARG_MEET_ID = "meeting_Id";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    public static final int ACTIVITY_REQUEST_DATE = 3;
    public static final int ACTIVITY_REQUEST_TIME = 4;


    public static AddMeetingFragment newInstance (UUID callId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEET_ID , callId);

        AddMeetingFragment fragment = new AddMeetingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID callId = (UUID)getArguments().getSerializable(ARG_MEET_ID);
        mMeeting = ColdCallingLab.get(getActivity()).getMeeting(callId);


    }
    @Override
    public void onPause(){
        super.onPause();
        ColdCallingLab.get(getActivity()).updateMeeting(mMeeting);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_fragment_meeting, container, false);
        nameMeeting = (EditText) view.findViewById(R.id.name_meeting_edit_text);
        placeMeeting = (EditText) view.findViewById(R.id.place_meeting_edit_text);
        mapButton = (FloatingActionButton)view.findViewById(R.id.map_button);
        dateMeetingButton = (Button)view.findViewById(R.id.date_meeting);
        dateMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet(getContext())){
                    FragmentManager manager = getFragmentManager();
                    DateFragment dialog = DateFragment.newInstance(mMeeting.getmDate());
                    dialog.setTargetFragment(AddMeetingFragment.this , REQUEST_DATE);
                    dialog.show(manager ,DIALOG_DATE);
                }else {
                    Intent intent = new Intent(getContext() , DateActivity.class);
                    intent.putExtra(EXTRA_DATE , mMeeting.getmDate());
                    startActivityForResult(intent ,ACTIVITY_REQUEST_DATE );
                }
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , MapsActivity.class);
                startActivity(intent);
            }
        });
        nameMeeting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMeeting.setNameCompanyMeeting(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        placeMeeting.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMeeting.setPlaceMeeting(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
nameMeeting.setText(mMeeting.getNameCompanyMeeting());
placeMeeting.setText(mMeeting.getPlaceMeeting());
        return view;
    }
    private boolean isTablet (Context context){
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}