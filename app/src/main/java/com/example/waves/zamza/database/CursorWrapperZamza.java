package com.example.waves.zamza.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.waves.zamza.ColdCalling;

import java.util.UUID;

import static com.example.waves.zamza.database.DbSchema.*;

public class CursorWrapperZamza extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorWrapperZamza(Cursor cursor) {
        super(cursor);
    }
    public ColdCalling getColdCalling (){
        String uuidString = getString(getColumnIndex(Table.Cols.UUID));
        String name = getString(getColumnIndex(Table.Cols.NAME));
        String number = getString(getColumnIndex(Table.Cols.NUMBER));
        String position = getString(getColumnIndex(Table.Cols.POSITION));
        String company = getString(getColumnIndex(Table.Cols.COMPANY));
        String mail = getString(getColumnIndex(Table.Cols.MAIL));


        ColdCalling coldCalling = new ColdCalling(UUID.fromString(uuidString));
        coldCalling.setNameCalling(name);
        coldCalling.setNumberCalling(number);

        return coldCalling;
    }
}
