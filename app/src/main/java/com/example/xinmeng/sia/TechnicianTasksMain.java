package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TechnicianTasksMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);

        TextView location = (TextView) findViewById(R.id.Location);
        TextView landing_Time = (TextView) findViewById(R.id.landing_time);
        TextView takeoff_time = (TextView) findViewById(R.id.takeoff_time);

        //location.setText();
        //landing_Time.setText();
        //takeoff_time.setText();
    }

    public void taskDetail(View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
        startActivity(intent);
    }
}
