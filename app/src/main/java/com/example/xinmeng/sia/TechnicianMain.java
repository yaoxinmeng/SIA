package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class TechnicianMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_main);


    }

    public void tasks(View view)
    {
        Intent intent = new Intent(TechnicianMain.this, TechnicianTasksMain.class);
        startActivity(intent);
    }

    public void equipment(View view)
    {
        Intent intent = new Intent(TechnicianMain.this, TechnicianEquipment.class);
        startActivity(intent);
    }
}
