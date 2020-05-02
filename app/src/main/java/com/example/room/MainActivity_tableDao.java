package com.example.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
//for main activity
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



    //for individual chat
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_t2(Msg msg);
    @Delete
    void delete_t2(Msg msg);
    @Query("DELETE FROM msg_table")
    void deleteAll_t2();
    @Query("SELECT * FROM msg_table where contactNumber = :contactNumber")
    LiveData<List<Msg>> getAll_t2(String contactNumber);

}


