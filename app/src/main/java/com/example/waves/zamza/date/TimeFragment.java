package com.example.waves.zamza.date;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import com.example.waves.zamza.R;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeFragment extends DialogFragment {
    public static final String ARG_DATE = "date";
    public static final String EXTRA_TIME = "com.example.waves.zamza.time";

    private TimePicker mTimePicker;
    private Button mOkButton;
    private Button mCancelButton;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState){
        Date date = (Date)getArguments().getSerializable(ARG_DATE);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time , null);

        mTimePicker = (TimePicker) view.findViewById(R.id.dialog_time_time_picker);
        mTimePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >=23){
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minutes);
        }else{
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minutes);
        }
        mOkButton= (Button)view.findViewById(R.id.save_button_time);
        mCancelButton = (Button)view.findViewById(R.id.cancel_button_time);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour;
                int minutes;

                if (Build.VERSION.SDK_INT >= 23) {
                    hour = mTimePicker.getHour();
                    minutes = mTimePicker.getMinute();
                } else {
                    hour = mTimePicker.getCurrentHour();
                    minutes = mTimePicker.getCurrentMinute();
                }
                Date newDate = new GregorianCalendar(year ,month, day , hour , minutes).getTime();
                sendResult(Activity.RESULT_OK , newDate);
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTargetFragment() != null){
                    dismiss();
                }else {
                    getActivity().finish();
                }
            }
        });
        return view;
    }
    private void sendResult (int resultCode , Date newDate){
        if (getTargetFragment() !=null){
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TIME , newDate);
            getTargetFragment().onActivityResult(getTargetRequestCode() , resultCode , intent);
            dismiss();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME , newDate);
        getActivity().setResult(resultCode , intent);
        getActivity().finish();
    }
    public static TimeFragment newInstance (Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE , date);
        TimeFragment fragment = new TimeFragment();
        fragment.setArguments(args);
        return fragment;

    }
}
