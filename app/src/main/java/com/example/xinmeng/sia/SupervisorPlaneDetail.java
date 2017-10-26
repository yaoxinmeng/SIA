package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SupervisorPlaneDetail extends AppCompatActivity {
    private EditText number_of_techs;
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
        number_of_techs = (EditText) findViewById(R.id.numberOfTechs);
    }

    public void autoAssignButton(View view)
    {
        NUMBER_OF_TECHS = Integer.parseInt(number_of_techs.getText().toString());
        if (NUMBER_OF_TECHS > plane.numberOfDefects)
        {
            plane.numberOfTechnicians =  NUMBER_OF_TECHS;
            plane.autoAssignTechnicians();

            Intent intent = new Intent(SupervisorPlaneDetail.this, SupervisorMain.class);
            startActivity(intent);
        }
        else
        {
            //error message because too many technicians are assigned
        }
    }

    public void manualAssignButton(View view)
    {
        NUMBER_OF_TECHS = Integer.parseInt(number_of_techs.getText().toString());
        if (NUMBER_OF_TECHS > plane.numberOfDefects)
        {
            //creates popup of tech id field and assign button
        }
        else
        {
            //error message because too many technicians are assigned
        }
    }

    public void manualAssignTechnician(View view)
    {
        for (Object child : Database.technicians)
        {
            Technicians tech = (Technicians) child;
            if (techID_field.getText().toString().equals(tech.ID))
            {
                tech.addPriorityPlane(plane);
                plane.assigned = true;

                NUMBER_OF_TECHS--;
                if(NUMBER_OF_TECHS == 0)
                {
                    //goes back to SupervisorMain page
                    Intent intent = new Intent(SupervisorPlaneDetail.this, SupervisorMain.class);
                    startActivity(intent);
                }
                else
                {
                    techID_field.setText("");
                    //popup message maybe?
                }
                break;
            }
        }
    }
}
