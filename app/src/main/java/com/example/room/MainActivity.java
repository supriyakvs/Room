package com.example.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final Myadapter adapter = new Myadapter();
        recyclerView.setAdapter(adapter);

        Dbviewmodel model = new ViewModelProvider(this).get(Dbviewmodel.class);
        model.getAll().observe(this, new Observer<List<MainActivity_table>>() {
            @Override
            public void onChanged(@Nullable List<MainActivity_table> mainActivity_tables) {
                //update recycler view
                adapter.set(mainActivity_tables);

            }

        });

    }




}

