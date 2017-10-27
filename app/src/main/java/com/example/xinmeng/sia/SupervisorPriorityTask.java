package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SupervisorPriorityTask extends AppCompatActivity {
    private EditText techID_field;
    private String plane_ID;
    private Plane plane;
    private int NUMBER_OF_TECHS;

    TextView techAssign;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_priority_task);

        Bundle extras = getIntent().getExtras();
        plane_ID = extras.getString("PLANE_ID");
        for (Plane child : Database.planes)
        {
            if (plane_ID.equals(child.regn))
            {
                plane = child;
                break;
            }
        }
        NUMBER_OF_TECHS = extras.getInt("NUMBER_OF_TECHS");

        techID_field = (EditText) findViewById(R.id.techIDField);
        techAssign = (TextView) findViewById(R.id.techLeft);

    }

    public void manualAssignTechnician(View view)
    {
        for (Technicians tech : Database.technicians)
        {
            if (techID_field.getText().toString().equals(tech.ID))
            {
                techAssign.setText(NUMBER_OF_TECHS + "Technicians left to Assign");
                tech.addPriorityPlane(plane);
                plane.assigned = true;
                //popup message to confirm allocation

                NUMBER_OF_TECHS--;


                if(NUMBER_OF_TECHS == 0)
                {
                    //goes back to SupervisorMain page
                    Intent intent = new Intent(SupervisorPriorityTask.this, SupervisorMain.class);
                    startActivity(intent);
                }
                else
                {
                    techID_field.setText("");
                }
                break;
            }
        }
    }

    public void backButton(View view)
    {
        Intent intent = new Intent(SupervisorPriorityTask.this, SupervisorPlaneDetail.class);
        intent.putExtra("PLANE_ID", plane_ID);
        startActivity(intent);
    }
}
