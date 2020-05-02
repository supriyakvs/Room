package com.example.room;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;

import static android.content.ContentValues.TAG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteHolder> {
    private LayoutInflater layoutInflater;
    private List<MainActivity_table> notes = new ArrayList<>();
    private OnChatSummaryListener onChatSummaryListener;

    public MyAdapter(Context context, OnChatSummaryListener onChatSummaryListener) {
        layoutInflater = LayoutInflater.from(context);
        this.onChatSummaryListener = onChatSummaryListener;
    }

    class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView sender;
        private TextView last_sms;
        private TextView ts;
        OnChatSummaryListener onChatSummaryListener;


        public NoteHolder(@NonNull View itemView, OnChatSummaryListener onChatSummaryListener) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            last_sms = itemView.findViewById(R.id.last_sms);
            ts = itemView.findViewById(R.id.timestamp);
            this.onChatSummaryListener = onChatSummaryListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onChatSummaryListener.onChatSummaryClick(getAdapterPosition());

        }

        public void setData(MainActivity_table current, int position) {
            this.sender.setText(current.getSender());
            String last_sms = current.getLast_sms();
            if (last_sms.length() >= 20) {
                last_sms = last_sms.substring(0, 19) + "...";
            }
            this.last_sms.setText(last_sms);

            Date date = current.getTs();
            Date currentDate = new Date();
            DateFormat formatter;
            String dateText;
            formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
            if (formatter.format(date).equals(formatter.format(currentDate))) {
                formatter = DateFormat.getTimeInstance(DateFormat.SHORT);
                dateText = formatter.format(date);
            } else {
                formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
                dateText = formatter.format(date);
            }
            this.ts.setText(dateText);
        }

    }

    public interface OnChatSummaryListener {
        void onChatSummaryClick(int position);
    }




    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item, parent, false);
       return new NoteHolder(itemView, onChatSummaryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        MainActivity_table current = notes.get(position);
        holder.setData(current,position);
    }


    @Override
    public int getItemCount() {
        if(notes!=null)
        return notes.size();
        else
            return 0;
    }

    public void set(List<MainActivity_table> m) {
        Log.i(TAG,"Inside set function of MyAdapter");
        this.notes = m;
        notifyDataSetChanged();
    }
    public MainActivity_table getMsgAt(int position){
        return notes.get(position);
    }

}
