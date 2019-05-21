package com.example.waves.zamza;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.UUID;

public class ColdCallingFragment extends Fragment {
    private static final String ARG_CALL_ID = "calling_Id";
    public static final int REQUEST_CONTACT = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 6;

    private FloatingActionButton contactBook;
    private ColdCalling mColdCalling;
    private EditText nameContact;
    private EditText numberContact ;
    private EditText positionContact ;
    private EditText companyContact;
    private EditText mailContact;
    private Button callButton;
    private Button makeAppointment;
    private Button successfullyButton;
    private Button notSuccessfulButton;
    private RelativeLayout relativeLayout;

    public static ColdCallingFragment newInstance (UUID callId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CALL_ID , callId);

        ColdCallingFragment fragment = new ColdCallingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID callId = (UUID)getArguments().getSerializable(ARG_CALL_ID);
        mColdCalling = ColdCallingLab.get(getActivity()).getColdCalling(callId);


}
@Override
public void onPause(){
        super.onPause();
        ColdCallingLab.get(getActivity()).updateNumber(mColdCalling);
}
    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container , Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.activity_cold_calling, container, false);
getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Intent addContactBook = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
        nameContact = (EditText)view.findViewById(R.id.name_contact_edit);
        numberContact = (EditText)view.findViewById(R.id.number_contact_edit);
        positionContact = (EditText)view.findViewById(R.id.position_contact_edit);
        companyContact = (EditText)view.findViewById(R.id.company_contact_edit);
        mailContact = (EditText)view.findViewById(R.id.mail_contact_edit);

        mailContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mColdCalling.setMailCalling(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        positionContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mColdCalling.setPositionCalling(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        companyContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mColdCalling.setCompanyCalling(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        numberContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            mColdCalling.setNumberCalling(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mColdCalling.setNameCalling(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        relativeLayout = (RelativeLayout)view.findViewById(R.id.cold_calling_rv);
        callButton = (Button)view.findViewById(R.id.call_contact);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + mColdCalling.getNumberCalling()));
                startActivity(intent);
                mColdCalling.setCallComplete(true);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_result_call , null);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                makeAppointment = (Button)mView.findViewById(R.id.make_appointment_button);
                makeAppointment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Meeting meeting = new Meeting();
                        ColdCallingLab.get(getActivity()).addMeeting(meeting);
                        Intent intent = AddMeetingActivity.newIntent(getActivity(),meeting.getUuidMeeting());
                        startActivity(intent);
                   makeAppointment.setEnabled(false);

                    }
                });
                successfullyButton = (Button)mView.findViewById(R.id.successfully_button);
                successfullyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mColdCalling.setResultCall(true);
                        callButton.setText(R.string.good_call);
                        callButton.setBackgroundResource(R.drawable.good_call);
                        dialog.dismiss();
                    }
                });
                notSuccessfulButton = (Button)mView.findViewById(R.id.not_successful_button);
                notSuccessfulButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mColdCalling.setResultCall(false);
                        callButton.setText(R.string.not_good_call);
                        callButton.setBackgroundResource(R.drawable.not_a_good_call);
                        dialog.dismiss();
                    }
                });
            }
        });
        contactBook = (FloatingActionButton)view.findViewById(R.id.contact_book);
        contactBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(addContactBook , REQUEST_CONTACT);
            }
        });


        if (mColdCalling.isCallComplete()) {
            if (mColdCalling.isResultCall()) {
                callButton.setText(R.string.good_call);
                callButton.setBackgroundResource(R.drawable.good_call);
            } else {
                callButton.setText(R.string.not_good_call);
                callButton.setBackgroundResource(R.drawable.not_a_good_call);
            }

        }else {

            callButton.setBackgroundResource(R.drawable.call_button);
        }

        nameContact.setText(mColdCalling.getNameCalling());
        numberContact.setText(mColdCalling.getNumberCalling());
        positionContact.setText(mColdCalling.getPositionCalling());
        companyContact.setText(mColdCalling.getCompanyCalling());
        mailContact.setText(mColdCalling.getMailCalling());
        return view;
    }

    private void deleteContact(){
        ColdCallingLab coldCallingLab = ColdCallingLab.get(getActivity());
        coldCallingLab.deleteContact(mColdCalling);

    }
    @Override
    public void onCreateOptionsMenu (Menu menu , MenuInflater inflater){
    inflater.inflate(R.menu.cold_calling_fragment_tool_bar  , menu);
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.delete_contact:
                deleteContact();
                getActivity().finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

@Override
public void onActivityResult (int requestCode , int result , Intent data){


        if (requestCode == REQUEST_CONTACT && data != null){
            Uri contactUri = data.getData();


            String[] queryField = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID
            };
            Cursor c = getActivity().getContentResolver().query(contactUri , queryField , null , null   , null  );

        try {
            if (c != null && c.getCount() == 0){
                return;
            }
            c.moveToFirst();
            int contactIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int contactIdIndex = c.getColumnIndex(ContactsContract.Contacts._ID);
            String nameContactCursor = c.getString(contactIndex);
            int contactId = c.getInt(contactIdIndex);

            mColdCalling.setNameCalling(nameContactCursor);
            mColdCalling.setContactId(contactId);

            nameContact.setText(mColdCalling.getNameCalling());
        }
        finally {
            c.close();
        }

        Cursor cursorPhone = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                new String[]{String.valueOf(mColdCalling.getContactId())}, null);
        try {
            if (cursorPhone == null || cursorPhone.getCount() == 0){
                return;
            }
            cursorPhone.moveToFirst();
            String contactNumber = cursorPhone.getString(0);

            mColdCalling.setNumberCalling(contactNumber);

            numberContact.setText(mColdCalling.getNumberCalling());

        }
        finally {
            cursorPhone.close();
          }
        }
    }
}
