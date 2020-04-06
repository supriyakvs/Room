package com.example.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MainActivity_table {
    @PrimaryKey(autoGenerate = true) @NonNull
    private int ID;

    private String sender;
    private String last_sms;
    private int timestamp;

    public MainActivity_table(String sender, String last_sms, int timestamp) {
        this.sender = sender;
        this.last_sms = last_sms;
        this.timestamp = timestamp;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getSender() {
        return sender;
    }

    public String getLast_sms() {
        return last_sms;
    }

    public int getTimestamp() {
        return timestamp;
    }
}

