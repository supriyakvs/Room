package com.example.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add;
    DbViewModel model;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        model = new ViewModelProvider(this).get(DbViewModel.class);
        Log.i(TAG,"after model object is created in main ");
        model.getAll().observe(this, new Observer<List<MainActivity_table>>() {
            @Override
            public void onChanged(@Nullable List<MainActivity_table> mainActivity_tables) {
                //update recycler view
                Log.i(TAG,"Creating recycler view");
                adapter.set(mainActivity_tables);

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
    }
    public void open_send_msg() {
        Intent intent = new Intent(this, SendMsg.class);
        startActivity(intent);
    }

}

