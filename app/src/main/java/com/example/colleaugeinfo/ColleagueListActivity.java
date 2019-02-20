package com.example.colleaugeinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.colleaugeinfo.model.Colleague;
import com.example.colleaugeinfo.viewmodel.ColleagueViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ColleagueListActivity extends AppCompatActivity implements Observer<List<Colleague>>, ColleagueAdapter.ClickListener {

    @BindView(R.id.rvColleague)
    RecyclerView rvColleague;
    private ColleagueViewModel colleagueViewModel;
    private ColleagueAdapter colleagueAdapter;
    private List<Colleague> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initInstance();
        toolbarWithoutDrawer();
    }

    private void initInstance() {
        list = new ArrayList<>();
        colleagueViewModel = ViewModelProviders.of(this).get(ColleagueViewModel.class);
        Observer<List<Colleague>> observer = this;
        colleagueViewModel.getAllColleague().observe(this, observer);
    }

    @Override
    public void onChanged(List<Colleague> colleagues) {
        // update Recyclerview
        generateRecyclerView(colleagues);
    }

    private void generateRecyclerView(List<Colleague> colleaguesList) {
        if (colleagueAdapter == null) {
            list.addAll(colleaguesList);
            colleagueAdapter = new ColleagueAdapter(ColleagueListActivity.this, list);
            colleagueAdapter.setClickListener(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            rvColleague.setLayoutManager(linearLayoutManager);
            rvColleague.setItemAnimator(new DefaultItemAnimator());
            rvColleague.setAdapter(colleagueAdapter);

        }
        else{
            list.clear();
            list.addAll(colleaguesList);
            colleagueAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_colleague, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
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

    @Override
    public void itemClicked(int pos) {
        Colleague colleague = list.get(pos);
        Intent intent = new Intent(ColleagueListActivity.this, AddColleagueActivity.class);
        intent.putExtra(Utils.COLLEAGUE_INFO, colleague);
        startActivity(intent);
    }
}
