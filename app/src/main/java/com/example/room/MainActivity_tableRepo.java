package com.example.room;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity_tableRepo {

    private MainActivity_tableDao mainActivity_tableDao;
    private LiveData<List<MainActivity_table>> allMainMsg;
    private LiveData<List<Msg>> allMsg;


    public MainActivity_tableRepo(Application application) {
        MainActivity_tableDatabase database = MainActivity_tableDatabase.getInstance(application);
        mainActivity_tableDao = database.mainActivity_tableDao();
        allMainMsg = mainActivity_tableDao.getAll();

    }

    //main activity
    public void insert(MainActivity_table note) {
        new InsertNoteAsyncTask(mainActivity_tableDao).execute(note);
    }

    public void update(MainActivity_table note) {
        new UpdateNoteAsyncTask(mainActivity_tableDao).execute(note);
    }

    public void delete(MainActivity_table note) {
        new DeleteNoteAsyncTask(mainActivity_tableDao).execute(note);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(mainActivity_tableDao).execute();
    }

    public LiveData<List<MainActivity_table>> getAll() {

        Log.i(TAG, "Inside getAll of repo");
        return allMainMsg;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private InsertNoteAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(MainActivity_table... notes) {
            mainActivity_tableDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<MainActivity_table, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private UpdateNoteAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
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

    // for individual threads of contacts
    public void insert_t2(Msg m) {
        m.setTs(new Date());
        new InsertMsgAsyncTask(mainActivity_tableDao).execute(m);
    }

    public void delete_t2(Msg m) {
        new DeleteMsgAsyncTask(mainActivity_tableDao).execute(m);
    }

    public void deleteAll_t2() {
        new DeleteAllMsgAsyncTask(mainActivity_tableDao).execute();
    }

    public LiveData<List<Msg>> getAll_t2(String contactNumber) {
        allMsg = mainActivity_tableDao.getAll_t2(contactNumber);
        return allMsg;
    }

    private static class InsertMsgAsyncTask extends AsyncTask<Msg, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private InsertMsgAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(Msg... m) {
            mainActivity_tableDao.insert_t2(m[0]);
            return null;
        }
    }

    private static class DeleteMsgAsyncTask extends AsyncTask<Msg, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private DeleteMsgAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(Msg... m) {
            mainActivity_tableDao.delete_t2(m[0]);
            return null;
        }
    }

    private static class DeleteAllMsgAsyncTask extends AsyncTask<Void, Void, Void> {
        private MainActivity_tableDao mainActivity_tableDao;

        private DeleteAllMsgAsyncTask(MainActivity_tableDao mainActivity_tableDao) {
            this.mainActivity_tableDao = mainActivity_tableDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mainActivity_tableDao.deleteAll_t2();
            return null;
        }
    }
}





