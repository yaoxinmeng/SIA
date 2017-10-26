package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TechnicianTasksMain extends AppCompatActivity {
    private Technicians technician;
    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
    }

    public void taskDetail(View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
        startActivity(intent);
    }
}
