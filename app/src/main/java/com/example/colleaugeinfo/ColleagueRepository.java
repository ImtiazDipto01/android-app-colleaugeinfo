package com.example.colleaugeinfo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.colleaugeinfo.model.Colleague;
import com.example.colleaugeinfo.room.AppDatabase;
import com.example.colleaugeinfo.room.ColleagueDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ColleagueRepository {

    private ColleagueDao colleagueDao ;
    private LiveData<List<Colleague>> allColleague ;

    public ColleagueRepository(Context context){
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        colleagueDao = appDatabase.colleagueDao() ;
        allColleague = colleagueDao.getAllColleague() ;
    }

    public void insert(Colleague colleague){
        ProcessInsertColleague processInsertColleague = new ProcessInsertColleague(colleagueDao, colleague);
        processInsertColleague.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void delete(Colleague colleague){
        ProcessDeleteColleague processDeleteColleague = new ProcessDeleteColleague(colleagueDao, colleague);
        processDeleteColleague.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void update(Colleague colleague){
        ProcessUpdateColleague processUpdateColleague = new ProcessUpdateColleague(colleagueDao, colleague);
        processUpdateColleague.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public LiveData<List<Colleague>> getAllColleague(){
        return allColleague ;
    }

    @SuppressLint("StaticFieldLeak")
    private class ProcessInsertColleague extends AsyncTask<Void, Void, Void> {

        private Colleague colleague ;
        private ColleagueDao colleagueDao ;

        private ProcessInsertColleague(ColleagueDao colleagueDao, Colleague colleague){
            this.colleague = colleague ;
            this.colleagueDao = colleagueDao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.colleagueDao.insert(colleague);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class ProcessUpdateColleague extends AsyncTask<Void, Void, Void> {

        private Colleague colleague ;
        private ColleagueDao colleagueDao ;

        private ProcessUpdateColleague(ColleagueDao colleagueDao, Colleague colleague){
            this.colleague = colleague ;
            this.colleagueDao = colleagueDao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.colleagueDao.update(colleague);
            return null;
        }
    }


    @SuppressLint("StaticFieldLeak")
    private class ProcessDeleteColleague extends AsyncTask<Void, Void, Void> {

        private Colleague colleague ;
        private ColleagueDao colleagueDao ;

        private ProcessDeleteColleague(ColleagueDao colleagueDao, Colleague colleague){
            this.colleague = colleague ;
            this.colleagueDao = colleagueDao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.colleagueDao.delete(colleague);
            return null;
        }
    }

}
