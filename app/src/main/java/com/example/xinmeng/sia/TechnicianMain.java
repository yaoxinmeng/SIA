package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import static java.lang.System.currentTimeMillis;

public class TechnicianMain extends AppCompatActivity {
    private Technicians technician;

    TextView regn;
    TextView bay;
    TextView onTime;
    TextView arrTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_main);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
        setTexts();
        regn = (TextView) findViewById(R.id.flightRegister);
        bay = (TextView) findViewById(R.id.bayData);
        onTime = (TextView) findViewById(R.id.onTime);
        arrTime = (TextView) findViewById(R.id.arrData);
    }

    protected void onResume(Bundle savedInstanceState)
    {
        super.onResume();
        setTexts();

        //not sure if needed
        /*
        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
         */
    }

    public void tasks(View view)
    {
        Intent intent = new Intent(TechnicianMain.this, TechnicianTasksMain.class);
        intent.putExtra("TECH", (Serializable) technician);
        startActivity(intent);
    }

    public void equipment(View view)
    {
        Intent intent = new Intent(TechnicianMain.this, TechnicianEquipment.class);
        intent.putExtra("TECH", (Serializable) technician);
        startActivity(intent);
    }

    public void refresh(View view)
    {
        setTexts();
    }

    public void setTexts()
    {
        Plane firstPlane = technician.currentPlane;
        regn.setText(firstPlane.regn);
        bay.setText(firstPlane.bay);

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
            onTime.setText("Arrived");
        else if (firstPlane.delay)
            onTime.setText("Delayed");
        else
            onTime.setText("On Time");
    }
}
