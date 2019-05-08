package com.example.waves.zamza.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.waves.zamza.ColdCalling;
import com.example.waves.zamza.Meeting;

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
        String uuidString = getString(getColumnIndex(Table.Cols.UUID));
        String name = getString(getColumnIndex(Table.Cols.NAME));
        String number = getString(getColumnIndex(Table.Cols.NUMBER));
        String position = getString(getColumnIndex(Table.Cols.POSITION));
        String company = getString(getColumnIndex(Table.Cols.COMPANY));
        String mail = getString(getColumnIndex(Table.Cols.MAIL));
        long contact_id = getLong(getColumnIndex(Table.Cols.CONTACT_ID));
        int call_completed = getInt(getColumnIndex(Table.Cols.CALL_COMPLETED));
        int result_call = getInt(getColumnIndex(Table.Cols.RESULT_CALL));

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
