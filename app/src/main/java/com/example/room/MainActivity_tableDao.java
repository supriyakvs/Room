package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MainActivity_tableDao {
    @Insert
    void insert(MainActivity_table main);
    @Update
    void update(MainActivity_table main);
    @Delete
    void delete(MainActivity_table main);

    @Query("DELETE FROM MainActivity_table")
    void deleteAll();

    @Query("SELECT * FROM MainActivity_table")
    LiveData<List<MainActivity_table>> getAll();

}
