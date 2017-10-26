package com.example.xinmeng.sia;

import android.content.Intent;
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

        defectNo = (TextView) findViewById(R.id.show_defectno);
        description = (TextView) findViewById(R.id.show_description);
        raiseDate = (TextView) findViewById(R.id.show_dateraised);
        age = (TextView) findViewById(R.id.show_ageing);
        ATA = (TextView) findViewById(R.id.show_ata);
        category = (TextView) findViewById(R.id.show_category);
        deferral = (TextView) findViewById(R.id.show_defferal_reason);
        classcode = (TextView) findViewById(R.id.show_classcode);
        parts = (TextView) findViewById(R.id.show_partdetail);

        setTexts();
    }

    private void setTexts()
    {
        regn.setText(plane.regn);
        bay.setText(plane.bay);
        type.setText(plane.type);

        long arrtime = plane.arrTime.getTime();
        arrTime.setText(getTime(arrtime));
        long deptime = plane.depTIme.getTime();
        depTime.setText(getTime(deptime));

        if (currentTimeMillis() < plane.arrTime.getTime())
            status.setText("Arrived");
        else if (plane.delay)
            status.setText("Delayed");
        else
            status.setText("On Time");

        defectNo.setText(task.number);
        description.setText(task.description);
        raiseDate.setText(task.dateRaised);
        age.setText(task.age);
        ATA.setText(task.ata);
        category.setText(task.category);
        deferral.setText(task.deferralReason);
        parts.setText(task.parts);
        switch (task.classCode)
        {
            case Database.Economy:
                classcode.setText("Economy");
                break;
            case Database.Premium:
                classcode.setText("Premium");
                break;
            case Database.Business:
                classcode.setText("Business");
                break;
            case Database.First:
                classcode.setText("First");
                break;
            default:
                classcode.setText("");
                break;
        }
    }

    private String getTime(long time)
    {
        float arrtime = (float) time;
        int hour = (int) arrtime;
        int minute = (int) (100 * (arrtime - ((float) hour)));
        if (hour < 10)
        {
            if (minute < 10)
                return "0" + String.valueOf(hour) + ":0" + String.valueOf(minute);
            else
                return "0" + String.valueOf(hour) + ":" + String.valueOf(minute);
        }
        else
        {
            if (minute < 10)
                return String.valueOf(hour) + ":0" + String.valueOf(minute);
            else
                return String.valueOf(hour) + ":" + String.valueOf(minute);
        }
    }

    private void back()
    {
        Intent intent = new Intent(TechnicianTaskDetail.this, TechnicianTasksMain.class);
        startActivity(intent);
    }

    public void resolvedButton(View view)
    {
        task.resolved = true;
        back();
    }

    public void unresolvedButton(View view)
    {
        //popup
    }

    public void backButton(View view)
    {
        back();
    }
}
