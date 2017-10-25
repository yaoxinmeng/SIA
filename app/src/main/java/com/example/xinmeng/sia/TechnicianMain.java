package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.io.Serializable;

public class TechnicianMain extends AppCompatActivity {
    private Technicians technician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_main);

        Bundle extras = getIntent().getExtras();
        technician = (Technicians) extras.getSerializable("TECH");

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
}
