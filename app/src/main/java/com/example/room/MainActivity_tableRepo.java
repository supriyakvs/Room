package com.example.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivity_tableRepo {


    private MainActivity_tableDao dao;
    private LiveData<List<MainActivity_table>> allNotes;

    public MainActivity_tableRepo(Application application)
    {
        MainActivity_tableDatabase database = MainActivity_tableDatabase.getInstance(application);
        dao = database.Dao();
        allNotes = dao.getAll();

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
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao MainActivity_tableDao;

        private InsertNoteAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.MainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            MainActivity_tableDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao MainActivity_tableDao;

        private UpdateNoteAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.MainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            MainActivity_tableDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao MainActivity_tableDao;


        private DeleteNoteAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.MainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            MainActivity_tableDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity_tableDao MainActivity_tableDao;

        private DeleteAllAsyncTask(MainActivity_tableDao MainActivity_tableDao) {
            this.MainActivity_tableDao = MainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainActivity_tableDao.deleteAll();
            return null;
        }
    }
}
