package com.example.waves.zamza.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.waves.zamza.ColdCalling;

import java.util.UUID;

import static com.example.waves.zamza.database.DbSchema.*;

public class CursorWrapperContacts extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorWrapperContacts(Cursor cursor) {
        super(cursor);
    }
    public ColdCalling getColdCalling (){
        String uuidString = getString(getColumnIndex(TableContacts.Cols.UUID));
        String name = getString(getColumnIndex(TableContacts.Cols.NAME));
        String number = getString(getColumnIndex(TableContacts.Cols.NUMBER));
        String position = getString(getColumnIndex(TableContacts.Cols.POSITION));
        String company = getString(getColumnIndex(TableContacts.Cols.COMPANY));
        String mail = getString(getColumnIndex(TableContacts.Cols.MAIL));
        long contact_id = getLong(getColumnIndex(TableContacts.Cols.CONTACT_ID));
        int call_completed = getInt(getColumnIndex(TableContacts.Cols.CALL_COMPLETED));
        int result_call = getInt(getColumnIndex(TableContacts.Cols.RESULT_CALL));

        ColdCalling coldCalling = new ColdCalling(UUID.fromString(uuidString));
        coldCalling.setNameCalling(name);
        coldCalling.setNumberCalling(number);
        coldCalling.setPositionCalling(position);
        coldCalling.setCompanyCalling(company);
        coldCalling.setMailCalling(mail);
        coldCalling.setContactId(contact_id);
        coldCalling.setCallComplete(call_completed !=0);
        coldCalling.setResultCall(result_call !=0);

        return coldCalling;
    }
}
