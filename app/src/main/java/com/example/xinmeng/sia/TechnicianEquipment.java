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
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;
    private String techID;
    ArrayList<DefectsFetcher> DefectsList = new ArrayList<>();

    private Plane firstPlane;
    private ArrayList<String> equipment = new ArrayList<>();



    TextView regn;


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
//        firstPlane = new Plane("test", "test", new Date(123456), new Date(123456));
        for (Defects child : firstPlane.defects)
        {
            String parts = child.parts;
            equipment.add(parts);
        }

        regn = (TextView) findViewById(R.id.display_acregn);


        setTexts();
    }

    private void setTexts()
    {
        regn.setText(firstPlane.regn);
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
