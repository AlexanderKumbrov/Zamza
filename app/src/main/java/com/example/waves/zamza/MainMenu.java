package com.example.waves.zamza;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private TextView textView;
    String name ;
    String number ;
    protected void  onCreate (Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_added_number);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    textView = (TextView)findViewById(R.id.name_contact);
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
    public void createContact (){
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
        long rowContacrId = ContentUris.parseId(rawContactUri);
        ContentValues values = new ContentValues();
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rowContacrId);
        values.put(ContactsContract.Data.MIMETYPE , ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Robert Smith");
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

    }
    public void test (View view){

Intent callIntent = new Intent(Intent.ACTION_PICK , ContactsContract.Contacts.CONTENT_URI);
startActivityForResult(callIntent , 1 );

    }
    @Override
    public void onActivityResult (int reqCode , int resultCode , Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor c =  managedQuery(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                 name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

            }
        }

textView.setText(name);
    }
    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
