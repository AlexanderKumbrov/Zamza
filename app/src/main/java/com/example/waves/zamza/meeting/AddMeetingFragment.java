package com.example.waves.zamza.meeting;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.waves.zamza.*;
import com.example.waves.zamza.date.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class AddMeetingFragment extends Fragment {

    private EditText nameMeeting;
    private EditText placeMeeting;
    private Meeting mMeeting;
    private FloatingActionButton mapButton;
    private Button dateMeetingButton;
    private Button timeMeetingButton;
    private Spinner importanceSpinner;

    private static final String ARG_MEET_ID = "meeting_Id";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_TIME = "time";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    public static final int ACTIVITY_REQUEST_DATE = 3;
    public static final int ACTIVITY_REQUEST_TIME = 4;
    public static final String TAG = "AddMeetingFragment";
    private static final int DATE_FORMAT = DateFormat.FULL;
    private static final int TIME_FORMAT = DateFormat.SHORT;


    public static AddMeetingFragment newInstance(UUID callId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEET_ID, callId);

        AddMeetingFragment fragment = new AddMeetingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID callId = (UUID) getArguments().getSerializable(ARG_MEET_ID);
        mMeeting = ColdCallingLab.get(getActivity()).getMeeting(callId);


    }

    @Override
    public void onPause() {
        super.onPause();
        ColdCallingLab.get(getActivity()).updateMeeting(mMeeting);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_fragment_meeting, container, false);
        nameMeeting = (EditText) view.findViewById(R.id.name_meeting_edit_text);
        placeMeeting = (EditText) view.findViewById(R.id.place_meeting_edit_text);
        mapButton = (FloatingActionButton) view.findViewById(R.id.map_button);
        importanceSpinner = (Spinner)view.findViewById(R.id.importance_meeting);
        importanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s  = String.valueOf(importanceSpinner.getSelectedItem());
                mMeeting.setImportance(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dateMeetingButton = (Button)view.findViewById(R.id.date_meeting);
        dateMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet(getContext())) {
                    FragmentManager manager = getFragmentManager();
                    DateFragment dialog = DateFragment.newInstance(mMeeting.getmDate());
                    dialog.setTargetFragment(AddMeetingFragment.this, REQUEST_DATE);
                    dialog.show(manager, DIALOG_DATE);

                } else {
                    Intent intent = new Intent(getContext(), DateActivity.class);
                    intent.putExtra(EXTRA_DATE, mMeeting.getmDate());
                    startActivityForResult(intent, ACTIVITY_REQUEST_DATE);
                }
            }
        });
        timeMeetingButton = (Button)view.findViewById(R.id.time_meeting);
        timeMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet(getActivity())){
                    FragmentManager manager = getFragmentManager();
                    TimeFragment fragment = TimeFragment.newInstance(mMeeting.getmDate());
                    fragment.setTargetFragment(AddMeetingFragment.this , REQUEST_TIME);
                    fragment.show(manager , DIALOG_TIME);
                }else {
                    Intent intent = new Intent(getContext() , TimeActivity.class);
                    intent.putExtra(EXTRA_TIME , mMeeting.getmDate());
                    startActivityForResult(intent , ACTIVITY_REQUEST_TIME);
                }
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE || requestCode == REQUEST_TIME ||
                requestCode == ACTIVITY_REQUEST_DATE || requestCode == ACTIVITY_REQUEST_TIME) {
            final Date date;
            if (requestCode == REQUEST_DATE || requestCode == ACTIVITY_REQUEST_DATE) {
                date = (Date) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            } else {
                date = (Date) data.getSerializableExtra(TimeFragment.EXTRA_TIME);
            }

            mMeeting.setmDate(date);
            dateMeetingButton.setText(DateFormatterKt.formatDateAsString(DATE_FORMAT , date));
            timeMeetingButton.setText(DateFormatterKt.formatDateAsTimeString(TIME_FORMAT ,date));
        }
    }



    private boolean isTablet (Context context){
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}