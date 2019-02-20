package com.example.colleaugeinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.colleaugeinfo.model.Colleague;
import com.example.colleaugeinfo.room.AppDatabase;
import com.example.colleaugeinfo.viewmodel.ColleagueViewModel;

import java.util.Calendar;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddColleagueActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etOffice)
    EditText etOffice;
    @BindView(R.id.etDesignation)
    EditText etDesignation;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.btnSave)
    Button btnSave;

    private Colleague colleague;
    private AppDatabase appDb;
    private ColleagueViewModel colleagueViewModel ;
    private static final String TAG = "AddColleagueActivity";
    private int updateFlag = 0 ;
    public static final int UPDATE = 1, ADD_NEW = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_colleague);
        ButterKnife.bind(this);
        initInstance();
        getValuesFromIntent();
    }

    private void getValuesFromIntent() {
        if(getIntent().hasExtra(Utils.COLLEAGUE_INFO)){
            updateFlag = UPDATE ;
            colleague = (Colleague) getIntent().getSerializableExtra(Utils.COLLEAGUE_INFO);
            etName.setText(colleague.getName());
            etDesignation.setText(colleague.getDesignation());
            etAddress.setText(colleague.getAddress());
            etOffice.setText(colleague.getOfficeName());
            etPhone.setText(colleague.getPhone());
        }
    }

    private void initInstance() {
        colleagueViewModel = ViewModelProviders.of(this).get(ColleagueViewModel.class);
    }

    public String generateUniqueProcessId(String processType) {
        int randomPIN = (int) (Math.random() * 999) + 100;
        Log.e(TAG, "randomPIN: " + randomPIN);
        String process_id = "";
        Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
        long millies = mCalendar.getTimeInMillis();
        Log.e("GMT:", String.valueOf(millies));
        process_id = String.valueOf(randomPIN) + processType + String.valueOf(millies);
        Log.e(TAG, "generateUniqueProcessId: " + process_id);
        return process_id;
    }

    @OnClick(R.id.btnSave)
    public void onSaveClicked() {
        if(updateFlag == ADD_NEW){
            colleague = new Colleague(generateUniqueProcessId("CL"),
                    etName.getText().toString(),
                    etDesignation.getText().toString(),
                    etAddress.getText().toString(),
                    etPhone.getText().toString(),
                    etOffice.getText().toString());

            colleagueViewModel.insert(colleague);
            finish();
        }
        else{

        }
    }
}
