package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xinmeng.sia.Adapter.SpareListAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;
    private String techID;
    ArrayList<DefectsFetcher> DefectsList = new ArrayList<>();

    private Plane firstPlane;
    private ArrayList<String> equipment = new ArrayList<>();


    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView regn;
    ImageView delayed;
    ImageView onTime;
    ImageView arrived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_equipment);





        ListView mListView = (ListView) findViewById(R.id.spare_list);
        SpareListAdapter adapter = new SpareListAdapter(this,R.layout.tech_tabinterface,equipment);
        mListView.setAdapter(adapter);


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
        for (Defects child : firstPlane.defects)
        {
            String parts = child.parts;
            equipment.add(parts);
        }

        regn = (TextView) findViewById(R.id.flight_regn);
        bay = (TextView) findViewById(R.id.flight_bay);
        arrTime = (TextView) findViewById(R.id.flight_time_arrival);
        depTime = (TextView) findViewById(R.id.flight_time_departure);
        type = (TextView) findViewById(R.id.flight_actype);
        delayed =(ImageView) findViewById(R.id.status_delayed);
        onTime =(ImageView) findViewById(R.id.status_ontime);
        arrived = (ImageView) findViewById(R.id.status_arrived);

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

        if (currentTimeMillis() < firstPlane.arrTime.getTime()){
            onTime.setVisibility(View.INVISIBLE);
            delayed.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.VISIBLE);}

        else if (firstPlane.delay){
            onTime.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.INVISIBLE);
            delayed.setVisibility(View.VISIBLE);}
        else {
            delayed.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.INVISIBLE);
            onTime.setVisibility(View.VISIBLE);
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

    public void backButton(View view)
    {
        Intent intent = new Intent(TechnicianEquipment.this, TechnicianMain.class);
        startActivity(intent);
    }
}
