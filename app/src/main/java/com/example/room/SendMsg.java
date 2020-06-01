package com.example.room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SendMsg extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    public static final String EXTRA_PHONE =
            "com.example.room.EXTRA_PHONE";
    public static final String EXTRA_MSG =
            "com.example.room.EXTRA_MSG";

    private EditText phoneNum;
    private EditText msg;
    private Button send;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_msg);

        phoneNum = findViewById(R.id.phone_num);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);

        checkForSmsPermission();



    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "PERMISSION NOT GRANTED!");
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            sendMsg();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // For the requestCode, check if permission was granted or not.
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable sms button.
                    sendMsg();
                } else {
                    // Permission denied.
                    Log.d(TAG, "PERMISSION NOT GRANTED!");
                    Toast.makeText(this, "PERMISSION NOT GRANTED!", Toast.LENGTH_SHORT).show();
                    // Disable the sms button.
                    return;
                }
            }
        }
    }

    public void sendMsg(){

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveMsg();
            }
        });
    }



    private void saveMsg(){
            String phone_num = phoneNum.getText().toString();
            String sms_msg = msg.getText().toString();
            Intent data = new Intent();

            if(phone_num.trim().isEmpty() || sms_msg.trim().isEmpty()){
                Toast.makeText(this, "Please enter valid details",Toast.LENGTH_SHORT).show();
                return;
            }
            if(phone_num.trim().length()!=10){
                Toast.makeText(this, "Please enter valid phone number",Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                data.putExtra(EXTRA_PHONE, phone_num);
                data.putExtra(EXTRA_MSG, sms_msg);
                setResult(RESULT_OK, data);
                Toast.makeText(this, "Message Sent",Toast.LENGTH_SHORT).show();

            }
        finish();

        }
}

