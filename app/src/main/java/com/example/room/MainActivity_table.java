package com.example.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class MainActivity_table {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    private String sender;
    private String last_sms;
    @TypeConverters({TimestampConverter.class})
    private Date ts;

    public MainActivity_table(String sender, String last_sms) {
        if (sender.startsWith("+91")) {
            this.sender = sender.substring(3);
        } else if (sender.startsWith("0")) {
            this.sender = sender.substring(1);
        } else {
            this.sender = sender;
        }
        this.last_sms = last_sms;

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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

}

    @Entity(tableName = "msg_table")
    class Msg {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private int id;
        private String msg;
        private String contactNumber;
        private String msg_type;
        @TypeConverters({TimestampConverter.class})
        private Date ts;

        public Msg(String contactNumber, String msg, String msg_type) {
            this.contactNumber = contactNumber;
            this.msg = msg;
            this.msg_type = msg_type;
        }

        public void setTs(Date ts) {
            this.ts = ts;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getMsg() {
            return msg;
        }

        public Date getTs() {
            return ts;
        }

        public String getMsg_type() {
            return msg_type;
        }
    }




