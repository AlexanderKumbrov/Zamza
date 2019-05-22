package com.example.waves.zamza;

import java.util.Date;
import java.util.UUID;

public class Meeting {
    private UUID uuidMeeting;
    private Date mDate;
    private String nameCompanyMeeting;
    private String PlaceMeeting;

    public Meeting(){
        this (UUID.randomUUID());
    }
    public Meeting(UUID id){
        uuidMeeting = id;

    }
    public UUID getUuidMeeting() {
        return uuidMeeting;
    }

    public void setUuidMeeting(UUID uuidMeeting) {
        this.uuidMeeting = uuidMeeting;
    }

    public String getNameCompanyMeeting() {
        return nameCompanyMeeting;
    }

    public void setNameCompanyMeeting(String nameCompanyMeeting) {
        this.nameCompanyMeeting = nameCompanyMeeting;
    }

    public String getPlaceMeeting() {
        return PlaceMeeting;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setPlaceMeeting(String placeMeeting) {
        PlaceMeeting = placeMeeting;
    }

}
