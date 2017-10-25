package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SupervisorPlaneDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_plane_detail);


        Bundle extras = getIntent().getExtras();
        Plane plane = (Plane)extras.getSerializable("PLANE");

        techID_field = (EditText) findViewById(R.id.techIDField);

    }

    public void manualAssignTechnician()
    {
        if (techID_field) =
    }
}
