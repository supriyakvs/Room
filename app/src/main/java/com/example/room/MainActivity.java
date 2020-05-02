package com.example.room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnChatSummaryListener{
    public final static int ADD_MSG_REQUEST = 1;
    DbViewModel dbViewModel;
    FloatingActionButton add;
    private List<MainActivity_table> chatSummaryList;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MyAdapter adapter = new MyAdapter(this, this);
        recyclerView.setAdapter(adapter);

        dbViewModel = new ViewModelProvider(this).get(DbViewModel.class);
        Log.i(TAG,"after model object is created in main ");
        dbViewModel.getAll().observe(this, new Observer<List<MainActivity_table>>() {
            @Override
            public void onChanged(@Nullable List<MainActivity_table> mainActivity_tables) {
                //update recycler view
                Log.i(TAG,"Creating recycler view");
                adapter.set(mainActivity_tables);
                chatSummaryList = mainActivity_tables;
            }

        });

        add =(FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG,"Add button clicked");
                open_send_msg();
            }
        });
//for swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dbViewModel.delete(adapter.getMsgAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Msg deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



    }
    public void open_send_msg() {
        Intent intent = new Intent(MainActivity.this, SendMsg.class);
        startActivityForResult(intent, ADD_MSG_REQUEST);

    }

    @Override
    public void onChatSummaryClick(int position) {
        Log.i(TAG, position + "item clicked");
        Intent i = new Intent(MainActivity.this, IndividualChatActivity.class);
        i.putExtra("contactNumber",chatSummaryList.get(position).getSender());
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MSG_REQUEST && resultCode == RESULT_OK){
            String phoneNum = null;
            if (data != null) {
                phoneNum = data.getStringExtra(SendMsg.EXTRA_PHONE);
            }
            String smsMsg = null;
            if (data != null) {
                smsMsg = data.getStringExtra(SendMsg.EXTRA_MSG);
            }
            MainActivity_table m = new MainActivity_table(phoneNum, smsMsg);
            dbViewModel.insert(m);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Not Done", Toast.LENGTH_SHORT).show();
        }

    }






}

