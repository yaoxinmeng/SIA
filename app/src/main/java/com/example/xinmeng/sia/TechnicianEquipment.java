package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;
    private Plane currentPlane;
    private List equipment = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_equipment);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
        currentPlane = (Plane) technician.planes.element();
        for (Object child : currentPlane.defects)
        {
            String parts = ((Defects) child).parts;
            equipment.add(parts);
        }

    }
}
