package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;
    private String techID;
    private Plane firstPlane;
    private ArrayList<String> equipment = new ArrayList<>();

    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView status;
    TextView regn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_equipment);

        Bundle extras = getIntent().getExtras();
        techID = extras.getString("TECH_ID");

        for (Technicians child : Database.technicians)
        {
            if(techID.equals(child.ID))
            {
                technician = child;
                break;
            }
        }
        firstPlane = technician.currentPlane;
        for (Object child : firstPlane.defects)
        {
            String parts = ((Defects) child).parts;
            equipment.add(parts);
        }

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
        regn.setText(firstPlane.regn);
        bay.setText(firstPlane.bay);
        type.setText(firstPlane.type);

        long arrtime = firstPlane.arrTime.getTime();
        arrTime.setText(getTime(arrtime));
        long deptime = firstPlane.depTIme.getTime();
        depTime.setText(getTime(deptime));

        if (currentTimeMillis() < firstPlane.arrTime.getTime())
            status.setText("Arrived");
        else if (firstPlane.delay)
            status.setText("Delayed");
        else
            status.setText("On Time");
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

    public void backButton(View view)
    {
        Intent intent = new Intent(TechnicianEquipment.this, TechnicianMain.class);
        startActivity(intent);
    }
}
