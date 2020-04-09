package com.example.room;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DbViewModel extends AndroidViewModel {

    private MainActivity_tableRepo repository;
    private LiveData<List<MainActivity_table>> all;

    public DbViewModel(@NonNull Application application) {
        super(application);
        repository = new MainActivity_tableRepo(application);
        all = repository.getAll();
    }

    public void insert(MainActivity_table note) {
        repository.insert(note);
    }

    public void update(MainActivity_table note) {
        repository.update(note);
    }

    public void delete(MainActivity_table note) {
        repository.delete(note);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<MainActivity_table>> getAll() {
        Log.i(TAG,"Inside getAll() of DBViewModel");
        return all;
    }

}
