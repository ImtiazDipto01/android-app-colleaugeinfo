package com.example.colleaugeinfo.viewmodel;

import android.app.Application;

import com.example.colleaugeinfo.ColleagueRepository;
import com.example.colleaugeinfo.model.Colleague;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ColleagueViewModel extends AndroidViewModel {

    private ColleagueRepository repository ;
    private LiveData<List<Colleague>> allColleague ;

    public ColleagueViewModel(@NonNull Application application) {
        super(application);
        repository = new ColleagueRepository(application.getApplicationContext());
    }

    public void insert(Colleague colleague){
        repository.insert(colleague);
    }

    public void delete(Colleague colleague){
        repository.delete(colleague);
    }

    public void update(Colleague colleague){
        repository.update(colleague);
    }

    public LiveData<List<Colleague>> getAllColleague(){
        allColleague = repository.getAllColleague() ;
        return allColleague ;
    }
}
