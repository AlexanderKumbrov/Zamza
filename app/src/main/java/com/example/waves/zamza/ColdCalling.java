package com.example.waves.zamza;

import java.util.UUID;

public class ColdCalling {
    private String nameCalling;
    private String numberCalling;
    private UUID uuidCalling;
    public ColdCalling (){
        this (UUID.randomUUID());
    }
    public ColdCalling (UUID id){
        uuidCalling = id;

    }
    public String getNumberCalling() {
        return numberCalling;
    }

    public void setNumberCalling(String numberCalling) {
        this.numberCalling = numberCalling;
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
