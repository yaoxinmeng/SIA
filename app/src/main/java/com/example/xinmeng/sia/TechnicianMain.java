package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class TechnicianMain extends AppCompatActivity {
    private Technicians technician;

    TextView regn = (TextView) findViewById(R.id.flightRegister);
    TextView bay = (TextView) findViewById(R.id.bayData);
    TextView onTime = (TextView) findViewById(R.id.onTime);
    TextView arrTime = (TextView) findViewById(R.id.arrData);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_main);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
        setTexts();
    }

    protected void onResume(Bundle savedInstanceState)
    {
        super.onResume();

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
        Plane firstPlane = (Plane) technician.planes.element();
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

        if (firstPlane.delay)
            onTime.setText("Delayed");
        else
            onTime.setText("On Time");
    }
}
