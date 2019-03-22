package com.example.waves.zamza;

import java.util.UUID;

public class ColdCalling {
    private String nameCalling;

    public long getNumberCalling() {
        return numberCalling;
    }

    public void setNumberCalling(long numberCalling) {
        this.numberCalling = numberCalling;
    }

    private long numberCalling;
    private UUID uuidCalling;
    private String positionCalling;
    private String companyCalling;
    private String mailCalling;
    public String getPositionCalling() {
        return positionCalling;
    }

    public void setPositionCalling(String positionCalling) {
        this.positionCalling = positionCalling;
    }

    public String getCompanyCalling() {
        return companyCalling;
    }

    public void setCompanyCalling(String companyCalling) {
        this.companyCalling = companyCalling;
    }

    public String getMailCalling() {
        return mailCalling;
    }

    public void setMailCalling(String mailCalling) {
        this.mailCalling = mailCalling;
    }


    public ColdCalling (){
        this (UUID.randomUUID());
    }
    public ColdCalling (UUID id){
        uuidCalling = id;

    }




    public UUID getUuidCalling() {
        return uuidCalling;
    }

    public void setUuidCalling(UUID uuidCalling) {
        this.uuidCalling = uuidCalling;
    }

    public String getNameCalling() {
        return nameCalling;
    }

    public void setNameCalling(String nameCalling) {
        this.nameCalling = nameCalling;
    }
}
