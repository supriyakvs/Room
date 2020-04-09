package com.example.room;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity_tableRepo {

    private MainActivity_tableDao dao;
    private LiveData<List<MainActivity_table>> all;

    public MainActivity_tableRepo(Application application)
    {
        MainActivity_tableDatabase database = MainActivity_tableDatabase.getInstance(application);
        dao = database.MainActivity_tableDao();
        all = dao.getAll();

    }

    public void insert(MainActivity_table note) {
        new InsertNoteAsyncTask(dao).execute(note);
    }

    public void update(MainActivity_table note) {
        new UpdateNoteAsyncTask(dao).execute(note);
    }

    public void delete(MainActivity_table note) {
        new DeleteNoteAsyncTask(dao).execute(note);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(dao).execute();
    }

    public LiveData<List<MainActivity_table>> getAll() {

        Log.i(TAG,"Inside getAll of repo");
        return all;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private InsertNoteAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.mainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            mainActivity_tableDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private UpdateNoteAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.mainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            mainActivity_tableDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;


        private DeleteNoteAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            mainActivity_tableDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private DeleteAllAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mainActivity_tableDao.deleteAll();
            return null;
        }
    }
}
