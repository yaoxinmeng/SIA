package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;
    private String techID;
    private Plane currentPlane;
    private ArrayList<String> equipment = new ArrayList<>();

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
        currentPlane = technician.planes.element();
        for (Object child : currentPlane.defects)
        {
            String parts = ((Defects) child).parts;
            equipment.add(parts);
        }
    }
}
