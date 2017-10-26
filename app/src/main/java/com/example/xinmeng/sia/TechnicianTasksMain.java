package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xinmeng.sia.Adapter.MyAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class TechnicianTasksMain extends AppCompatActivity {
    private Technicians technician;

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

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");

        regn = (TextView) findViewById(R.id.flight_regn);
        bay = (TextView) findViewById(R.id.flight_bay);
        arrTime = (TextView) findViewById(R.id.flight_time_arrival);
        depTime = (TextView) findViewById(R.id.flight_time_departure);
        type = (TextView) findViewById(R.id.flight_actype);
        status = (TextView) findViewById(R.id.flight_status);

        setTexts();
    }

    private void setTexts()
    {
        Plane firstPlane = technician.currentPlane;
        regn.setText(firstPlane.regn);
        bay.setText(firstPlane.bay);
        type.setText(firstPlane.type);

        float arrtime = (float) (firstPlane.arrTime.getTime());
        int hour = (int) arrtime;
        int minute = (int) (100 * (arrtime - ((float) hour)));
        if (hour < 10)
        {
            if (minute < 10)
                arrTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(minute));
            else
                arrTime.setText("0" + String.valueOf(hour) + ":" + String.valueOf(minute));
        }
        else
        {
            if (minute < 10)
                arrTime.setText(String.valueOf(hour) + ":0" + String.valueOf(minute));
            else
                arrTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
        }

        if (currentTimeMillis() < technician.currentPlane.arrTime.getTime())
            status.setText("Arrived");
        else if (firstPlane.delay)
            status.setText("Delayed");
        else
            status.setText("On Time");
    }

    public void taskDetail(View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
        startActivity(intent);
    }

    public void refresh(View view)
    {
        setTexts();
    }

    public void equipment (View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianEquipment.class);
        intent.putExtra("TECH", (Serializable) technician);
        startActivity(intent);
    }
}
