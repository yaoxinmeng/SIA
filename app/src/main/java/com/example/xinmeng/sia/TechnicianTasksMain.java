package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xinmeng.sia.Adapter.MyAdapter;

public class TechnicianTasksMain extends AppCompatActivity {
    private Technicians technician;
    DefectsFetcher[] DefectsFetcherList;
    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView status;
    TextView regn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);

/*
        MyAdapter<DefectsFetcher> defectsAdapter = new MyAdapter(this,R.layout.tech_tabinterface,DefectsFetcherList);
        ListView defectsList =  (ListView) findViewById(R.id.detail_list);
        defectsList.setAdapter(defectsAdapter);*/

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");

        regn = (TextView) findViewById(R.id.flight_regn);
        bay = (TextView) findViewById(R.id.flight_bay);
        arrTime = (TextView) findViewById(R.id.flight_time_arrival);
        depTime = (TextView) findViewById(R.id.flight_time_departure);
        type = (TextView) findViewById(R.id.flight_actype);
        status = (TextView) findViewById(R.id.flight_status);
    }

    public void taskDetail(View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
        startActivity(intent);
    }
}
