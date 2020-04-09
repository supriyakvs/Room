package com.example.room;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SecondActivity_table {
    @PrimaryKey(autoGenerate = true) @NonNull
    private int ID;

    private String sender;
    private String receiver;
    private int timestamp;
    private String sms_body;
    private int sms_type;

    public SecondActivity_table(String sender, String receiver, int timestamp, String sms_body, int sms_type) {
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.sms_body = sms_body;
        this.sms_type = sms_type;
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

    public String getReceiver() {
        return receiver;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getSms_body() {
        return sms_body;
    }

    public int getSms_type() {
        return sms_type;
    }
}
