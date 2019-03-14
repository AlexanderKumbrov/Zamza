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
        String title = getString(getColumnIndex(Table.Cols.TITLE));
        String number = getString(getColumnIndex(Table.Cols.NUMBER));


        ColdCalling coldCalling = new ColdCalling(UUID.fromString(uuidString));
        coldCalling.setNameCalling(title);
        coldCalling.setNumberCalling(number);

        return coldCalling;
    }
}
