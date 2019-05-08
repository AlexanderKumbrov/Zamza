package com.example.waves.zamza.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.waves.zamza.Meeting;
import com.example.waves.zamza.database.DbSchema.Table;

import java.util.UUID;

public class CursorWrapperMeeting extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorWrapperMeeting(Cursor cursor) {
        super(cursor);
    }
    public Meeting getMeeting (){
    String uuidMeetingSting = getString(getColumnIndex(Table.Cols.UUID_MEETING));




    Meeting meeting = new Meeting(UUID.fromString(uuidMeetingSting));

    return meeting;

    }
}
