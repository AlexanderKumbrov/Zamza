package com.example.waves.zamza;

import android.app.Activity;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private TextView textView;
    private TextView allNumber;
    String name ;
    String number ;
    private Button okButton ;
    protected void  onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_added_number);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textView = (TextView)findViewById(R.id.name_contact);
        allNumber = (TextView)findViewById(R.id.all_number);
        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllContacts();
            }
        });
    }
    public void cold_calling (View view){
        Intent cold = new Intent(this , ColdCallingActivity.class);
        startActivity(cold);
    }
    public void debt_repayment_term(View view){
        Intent debt = new Intent(this ,DebtRepaymentTermActivity.class);
        startActivity(debt);
    }
    public void meetings(View view){
        Intent meetings = new Intent(this ,MeetingsActivity.class);
        startActivity(meetings);
    }
    public void sales_training(View view){
        Intent sales = new Intent(this ,SalesTraining.class);
        startActivity(sales);
    }

//    public void test (View view){
//
//        getAllContacts();
////        Intent callIntent = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
////        startActivityForResult(callIntent , 1 );
//
//    }
    @Override
    public void onActivityResult (int reqCode , int resultCode , Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor c =  managedQuery(contactData, null, null, null, null);
            String id = contactData.getLastPathSegment();
//            c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
//            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? AND "+ ContactsContract.CommonDataKinds.Phone.TYPE + "= ?" ,new
//                    String[]{id ,String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_WORK)},null);
//
            if (c.moveToFirst()) {
                name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

            }
        }

        textView.setText(name);
    }
    private void getAllContacts(){
        StringBuilder stringBuilder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null ,null , null);
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

    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}