package com.example.room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.NoteHolder>
{ private List<MainActivity_table> notes = new ArrayList<>();
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
       return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        MainActivity_table current = notes.get(position);
        holder.sender.setText(current.getSender());
        holder.last_sms.setText(current.getLast_sms());
        holder.timestamp.setText(current.getTimestamp());

    }


    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void set(List<MainActivity_table> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView sender;
        private TextView last_sms;
        private TextView timestamp;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            last_sms = itemView.findViewById(R.id.last_sms);
            timestamp = itemView.findViewById(R.id.timestamp);

        }
    }
}
