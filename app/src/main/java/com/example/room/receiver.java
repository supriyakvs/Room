package com.example.room;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.content.BroadcastReceiver;

public class receiver extends BroadcastReceiver {
    private String TAG = this.getClass().getSimpleName();

    DbViewModel vm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.i(TAG,"Message Received");
        if(bundle!=null){
            final Object[] pdusObj = (Object[])bundle.get("pdus");
            String format = bundle.get("format").toString();

            for(int i=0; i<pdusObj.length;i++){

                SmsMessage message = SmsMessage.createFromPdu((byte[])pdusObj[i],format);
                String recieverPhn = message.getDisplayOriginatingAddress();
                String messageBody = message.getDisplayMessageBody();
                Long timestamp = message.getTimestampMillis();

                Log.i(TAG,"Phone "+recieverPhn);
                Log.i(TAG, "Message Body "+messageBody);
                Log.i(TAG, "timestamp "+timestamp);

                vm = new DbViewModel((Application) context);
                MainActivity_table m = new MainActivity_table(recieverPhn,messageBody);
                vm.insert(m);
                Msg m2 = new Msg(recieverPhn,messageBody,"received");
                vm.insert_t2(m2);

                Toast.makeText(context, "Message Received",Toast.LENGTH_LONG).show();

            }
        }
    }


    
}
