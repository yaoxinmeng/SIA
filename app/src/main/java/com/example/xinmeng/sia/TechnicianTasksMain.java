package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TechnicianTasksMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);
    }

    public void taskDetail(View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
        startActivity(intent);
    }
}
