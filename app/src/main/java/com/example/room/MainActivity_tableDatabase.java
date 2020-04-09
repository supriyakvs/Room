package com.example.room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import static androidx.constraintlayout.widget.Constraints.TAG;

@Database(entities = {MainActivity_table.class}, version = 1)
public abstract class MainActivity_tableDatabase extends RoomDatabase {

    public abstract MainActivity_tableDao mainActivity_tableDao();
    private static MainActivity_tableDatabase instance;


    public static synchronized MainActivity_tableDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MainActivity_tableDatabase.class, "MainActivity_tableDatabase")
                    .fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
            Log.i(TAG,"Instance created in MainActivity_tableDatabase ");
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
            Log.i(TAG,"in RoomDatabase.Callback");
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private PopulateDbAsyncTask(MainActivity_tableDatabase db) {
            mainActivity_tableDao = db.mainActivity_tableDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            mainActivity_tableDao.insert(new MainActivity_table("8527343337", "Description 1", 1));
            mainActivity_tableDao.insert(new MainActivity_table("8527343338", "Description 2", 2));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));
            mainActivity_tableDao.insert(new MainActivity_table("8527343339", "Description 3", 3));

            return null;
        }
    }
}


