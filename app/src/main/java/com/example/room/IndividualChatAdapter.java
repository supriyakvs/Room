package com.example.room;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class IndividualChatAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Msg> individualChatList;

    private static final int SMS_TYPE_SENT = 0;
    private static final int SMS_TYPE_RECEIVE = 1;

    public IndividualChatAdapter(Context context) {
        mContext = context;
      
    }


    @Override
    public int getItemCount() {
        if (individualChatList != null) {
            return individualChatList.size();
        } else {
            return 0;
        }
    }

    public void setChatNotes(List<Msg> individualChatList) {
        this.individualChatList = individualChatList;
        notifyDataSetChanged();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    public int getItemViewType(int position) {
        Msg message = individualChatList.get(position);

        if (message.getMsg_type().equals("0")) {
            return SMS_TYPE_SENT;
        } else {
            return SMS_TYPE_RECEIVE;
        }
    }


    // Inflates the appropriate layout according to the ViewType.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == SMS_TYPE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.send_msg_item, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == SMS_TYPE_RECEIVE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.receive_msg_item, parent, false);
            return new ReceivedMessageHolder(view);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Msg message = individualChatList.get(position);

        switch (holder.getItemViewType()) {
            case SMS_TYPE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case SMS_TYPE_RECEIVE:
                ((ReceivedMessageHolder) holder).bind(message);
        }

    }


    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }

        void bind(Msg message) {
            messageText.setText(message.getMsg());
            //HAVE TO INFLATE TIME
        }
    }


    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;


        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);

        }

        void bind(Msg message) {
            messageText.setText(message.getMsg());
            nameText.setText(message.getContactNumber());


            // //HAVE TO INFLATE TIME
            // Format the stored timestamp into a readable String using method.
            //timeText.setText(Utils.formatDateTime(message.getCreatedAt()));

        }
    }
}






