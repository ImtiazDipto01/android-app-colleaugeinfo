package com.example.colleaugeinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.colleaugeinfo.model.Colleague;
import com.example.colleaugeinfo.viewmodel.ColleagueViewModel;

import java.util.List;

public class ColleagueListActivity extends AppCompatActivity implements Observer<List<Colleague>> {

    private ColleagueViewModel colleagueViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance();
    }

    private void initInstance() {
        colleagueViewModel = ViewModelProviders.of(this).get(ColleagueViewModel.class);
        Observer<List<Colleague>> observer = this ;
        colleagueViewModel.getAllColleague().observe(this, observer);
    }

    @Override
    public void onChanged(List<Colleague> colleagues) {
        // update Recyclerview
    }
}
