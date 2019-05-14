package com.example.waves.zamza.database;

public class DbSchema {
    public static final class TableContacts {
        public static final String NAME = "contacts";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String NUMBER = "number";
            public static final String POSITION = "position";
            public static final String COMPANY = "company";
            public static final String MAIL = "mail";
            public static final String CONTACT_ID = "contact_id";
            public static final String CALL_COMPLETED = "call_completed";
            public static final String RESULT_CALL = "result_call";
        }
    }
    public static final class TableMeeting{
        public static final String  NAME = "meeting";
        public static final class Cols{
            public static final String UUID_MEETING ="uuid_meeting";
            public static final String NAME_COMPANY = "company";
            public static final String PLACE_MEETING = "place_meeting";
        }
    }

}
