package com.example.waves.zamza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.example.waves.zamza.date";
    private Date mDate;

    public static DateFragment newInstance (Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE , date);
        DateFragment fragment = new DateFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void sendResult (int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE , mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode() , resultCode , intent);
    }

@Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(mDate);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
    DatePicker datePicker = (DatePicker)view.findViewById(R.id.dialog_date_picker);
    datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mDate = new GregorianCalendar (year , monthOfYear , dayOfMonth).getTime();
            getArguments().putSerializable(EXTRA_DATE , mDate);
        }
    });
    return new AlertDialog.Builder(getActivity())
            .setView(view)
            .setTitle("Date")
            .setPositiveButton(
                    android.R.string.ok,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendResult(Activity.RESULT_OK);
                        }
                    })
            .create();
}
}
