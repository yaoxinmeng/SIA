package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import static java.lang.System.currentTimeMillis;


public class TechnicianTaskDetail extends AppCompatActivity {
    private Defects task;
    private Plane plane;
    private String taskID;
    private String planeID;

    //fragment
    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView status;
    TextView regn;

    //defect info
    TextView defectNo;
    TextView description;
    TextView raiseDate;
    TextView age;
    TextView ATA;
    TextView category;
    TextView deferral;
    TextView classcode;
    TextView parts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);

        Bundle extras = getIntent().getExtras();
        taskID = extras.getString("TASK_ID");
        planeID = extras.getString("PLANE_ID");

        for (Defects child : Database.defects)
        {
            if(taskID.equals(child.number))
            {
                task = child;
                break;
            }
        }
        for (Plane child : Database.planes)
        {
            if(planeID.equals(child.regn))
            {
                plane = child;
                break;
            }
        }

        regn = (TextView) findViewById(R.id.flight_regn);
        bay = (TextView) findViewById(R.id.flight_bay);
        arrTime = (TextView) findViewById(R.id.flight_time_arrival);
        depTime = (TextView) findViewById(R.id.flight_time_departure);
        type = (TextView) findViewById(R.id.flight_actype);
        status = (TextView) findViewById(R.id.flight_status);

        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);
        status = (TextView) findViewById(R.id.flight_status);

        setTexts();
    }

    private void setTexts()
    {
        regn.setText(plane.regn);
        bay.setText(plane.bay);
        type.setText(plane.type);

        float arrtime = (float) (plane.arrTime.getTime());
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

        if (currentTimeMillis() < plane.arrTime.getTime())
            status.setText("Arrived");
        else if (plane.delay)
            status.setText("Delayed");
        else
            status.setText("On Time");
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
