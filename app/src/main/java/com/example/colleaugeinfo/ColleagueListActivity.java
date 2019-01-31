package com.example.colleaugeinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        toolbarWithoutDrawer() ;
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

    private void generateRecyclerView(){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_colleague, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:{
                Intent intent = new Intent(ColleagueListActivity.this, AddColleagueActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void toolbarWithoutDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text_color_white));
        toolbar.setTitle(getResources().getString(R.string.my_colleague));
        setSupportActionBar(toolbar);
        //showCustomToolbar(toolbar);
    }


    private void showCustomToolbar(Toolbar toolbar) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);*/

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
