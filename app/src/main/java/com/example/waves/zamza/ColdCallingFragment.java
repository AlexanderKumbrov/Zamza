package com.example.waves.zamza;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class ColdCallingFragment extends Fragment {
    private final static String ARG_CALL_ID = "callingId";
    public static final int REQUEST_CONTACT = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 6;
    private String name ;
    private String number ;
    private FloatingActionButton contactBook;
    private Button okButton ;
    private ColdCalling mColdCalling;
    private EditText nameContact;
    private EditText numberContact ;

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
        mColdCalling =ColdCallingLab.get(getActivity()).getColdCalling(callId);


}
    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_cold_calling, container, false);
getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Intent addContactBook = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
        nameContact = (EditText)view.findViewById(R.id.name_contact_edit);
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
        numberContact = (EditText)view.findViewById(R.id.number_contact_edit);
        contactBook = (FloatingActionButton)view.findViewById(R.id.contact_book);
        contactBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(addContactBook , REQUEST_CONTACT);
            }
        });
        return view;
    }
//public static Intent newIntent (Context packageContext , UUID callingId){
//        Intent intent= new Intent(packageContext , ColdCallingFragment.class);
//        intent.putExtra(EXTRA_CALLING_ID , callingId);
//        return intent;
//}
@Override
public void onActivityResult (int requestCode , int result , Intent data){
        if (result != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CONTACT && data != null){
            Uri contactUri = data.getData();

            String [] queryField = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            Cursor c = getActivity().getContentResolver().query(contactUri , queryField , null , null , null);
            try {
                if (c.getCount() == 0){
                    return;
                }
                c.moveToFirst();
                String nameContactC  = c.getString(0);
//                mColdCalling.setNameCalling(nameContactC);
                nameContact.setText(nameContactC);
            }
            finally {
                c.close();
            }
            callNumber();
        }
}

private void callNumber(){
    Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI , new String[]
                    {ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"
            ,new String[]{String.valueOf(mColdCalling.getNumberCalling())},null);
    try {
        if (cursor == null || cursor.getCount() == 0){
            return;
        }
        cursor.moveToFirst();
        String phoneNumber = cursor.getString(0);
        numberContact.setText(phoneNumber);
    }
    finally {
        cursor.close();
    }
}
    private void getAllContacts(){
        StringBuilder stringBuilder = new StringBuilder();
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null ,null , null);
        }
        if (cursor.getCount()>0){
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null
                            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while (cursor1.moveToNext()) {
                        String phoneNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        stringBuilder.append("Контакт :").append(name).append(" , Номер :").append(phoneNumber).append("\n\n");
                    }
                    cursor1.close();
                }
            }
        }
        cursor.close();
    }
private void updateColdCalling (){

    }
}
