package com.example.waves.zamza;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewContactActivity extends AppCompatActivity {
    public static final int REQUEST_CONTACT = 1;

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 6;
    private String name ;
    private String number ;
    private FloatingActionButton contactBook;
    private Button okButton ;
    private ColdCalling mColdCalling;
    private EditText nameContact;
    private EditText numberContact ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_number);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Intent addContactBook = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
        nameContact = (EditText)findViewById(R.id.name_contact_edit);
        numberContact = (EditText)findViewById(R.id.number_contact_edit);
        contactBook = (FloatingActionButton)findViewById(R.id.contact_book);
        contactBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivityForResult(addContactBook , REQUEST_CONTACT);
            }
        });
}

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
            Cursor c = getContentResolver().query(contactUri , queryField , null , null , null);
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
    Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI , new String[]
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
        ContentResolver contentResolver = getContentResolver();
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
