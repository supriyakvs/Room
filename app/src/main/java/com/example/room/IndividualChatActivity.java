package com.example.room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndividualChatActivity extends AppCompatActivity {

    EditText messageBody;
    Button sendMsg;
    private MainActivity_tableRepo repo;
    private MainActivity_tableRepo repo1;
    private String contactNumber;
    private String TAG = this.getClass().getSimpleName();
    private IndividualChatAdapter individualChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_chat);
        Intent i = getIntent();
        contactNumber = i.getStringExtra("contactNumber");
        this.setTitle(contactNumber);

        messageBody = (EditText)findViewById(R.id.chat_box);
        sendMsg = (Button)findViewById(R.id.chat_box_send);

        Log.i(TAG, "Contact Number is "+contactNumber);
        Toast.makeText(this, "contact number is "+contactNumber, Toast.LENGTH_LONG).show();

        repo = new MainActivity_tableRepo(getApplication());
        repo1 = new MainActivity_tableRepo(getApplication());

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_individual_chat);
        final IndividualChatAdapter adapter = new IndividualChatAdapter(this);

        recyclerView.setAdapter(adapter);

        repo1.getAll_t2(contactNumber).observe(this, new Observer<List<Msg>>() {
            @Override
            public void onChanged(@Nullable List<Msg> individualChatList) {
                adapter.setChatNotes(individualChatList);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendMessage(View view){
        String message = messageBody.getText().toString();

        SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId());
        Log.i(TAG,"SubscriptionManager.getDefaultSmsSubscriptionId "+SubscriptionManager.getDefaultSubscriptionId());
        sms.sendTextMessage(contactNumber,null,message,null,null);


        MainActivity_table allChatSummaryEntity = new MainActivity_table(contactNumber,message);
        repo.insert(allChatSummaryEntity);

        Msg individualChatEntity = new Msg(contactNumber,message,"sent");
        repo1.insert_t2(individualChatEntity);

        messageBody.setText("");

        Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG);

    }
}