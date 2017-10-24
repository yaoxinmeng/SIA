package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TechnicianTaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_task_detail);
    }

    private void update()
    {
        while (true)
        {
            SystemClock.sleep(10000);
            //whatever that needs to be updated
        }
    }
}
