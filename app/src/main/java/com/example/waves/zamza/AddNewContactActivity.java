package com.example.waves.zamza;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddNewContactActivity extends AppCompatActivity {
    private TextView textView;
    private TextView allNumber;
    String name ;
    String number ;
    private Button okButton ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_number);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textView = (TextView)findViewById(R.id.name_contact);

    }

    public void contactBook (View view){
        Intent callIntent = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(callIntent , 1 );

    }
    @Override
    public void onActivityResult (int reqCode , int resultCode , Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor c =  managedQuery(contactData, null, null, null, null);
            String id = contactData.getLastPathSegment();
            if (c.moveToFirst()) {
                name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
            }
        }
        textView.setText(name);

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
        allNumber.setText(stringBuilder.toString());
    }
}
