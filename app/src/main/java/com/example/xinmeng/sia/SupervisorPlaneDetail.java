package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SupervisorPlaneDetail extends AppCompatActivity {

    private EditText techID_field;
    private Plane plane;
    private int NUMBER_OF_TECHS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_plane_detail);


        Bundle extras = getIntent().getExtras();
        plane = (Plane)extras.getSerializable("PLANE");

        techID_field = (EditText) findViewById(R.id.techIDField);

    }

    public void manualAssignTechnician()
    {
        for (Object child : Database.technicians)
        {
            Technicians tech = (Technicians) child;
            if (techID_field.getText().equals(tech.ID))
            {
                tech.addPlane(plane);
                plane.assigned = true;
            }
        }
    }
}
