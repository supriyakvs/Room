package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class Dbviewmodel extends ViewModel {

    private MainActivity_tableRepo repository;
    private LiveData<List<MainActivity_table>> all;

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
        return all;
    }

}
