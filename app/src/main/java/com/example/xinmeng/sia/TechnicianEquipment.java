package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TechnicianEquipment extends AppCompatActivity {
    private Technicians technician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_equipment);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");
    }
}
