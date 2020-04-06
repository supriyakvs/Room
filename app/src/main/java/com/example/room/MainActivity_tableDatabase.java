package com.example.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MainActivity_table.class}, version = 1)
public abstract class MainActivity_tableDatabase extends RoomDatabase {

    private static MainActivity_tableDatabase instance;

    public abstract MainActivity_tableDao Dao();

    public static synchronized MainActivity_tableDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MainActivity_tableDatabase.class, "MainActivity_tableDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity_tableDao dao;

        private PopulateDbAsyncTask(MainActivity_tableDatabase db) {
            dao = db.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new MainActivity_table("8527343337", "Description 1", 1));
            dao.insert(new MainActivity_table("8527343338", "Description 2", 2));
            dao.insert(new MainActivity_table("8527343339", "Description 3", 3));

            return null;
        }
    }
}


