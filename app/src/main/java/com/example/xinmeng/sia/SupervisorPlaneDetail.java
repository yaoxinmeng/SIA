package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.xinmeng.sia.Adapter.DefectsListAdapter;
import com.example.xinmeng.sia.Adapter.SpareListAdapter;

import java.util.ArrayList;


public class SupervisorPlaneDetail extends AppCompatActivity {
    ArrayList<DefectsFetcher> DefectsList = new ArrayList<>();
    private EditText number_of_techs;
    private String plane_ID;
    private Plane plane;
    private int NUMBER_OF_TECHS;
    private boolean unassigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_plane_detail);

        ListView mListView = (ListView) findViewById(R.id.defects_list);
        DefectsListAdapter adapter = new DefectsListAdapter(this,R.layout.sup_tabinterface,DefectsList);
        mListView.setAdapter(adapter);

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

        /*HEREHEREHEREnumber_of_techs = (EditText) findViewById(R.id.numberOfTechs);

        //Hides the assign buttons when unassigned = false
        /*
        Button autoAssign = (Button) findViewById(R.id.auto_assign);
        Button manualAssign = (Button) findViewById(R.id.manual_assign);

        if (plane.assigned)
        {
            autoAssign.setVisibility(View.INVISIBLE);
            manualAssign.setVisibility(View.INVISIBLE);
        }
        */
    }

    public void autoAssignButton(View view)
    {
        NUMBER_OF_TECHS = Integer.parseInt(number_of_techs.getText().toString());
        if (NUMBER_OF_TECHS <= plane.numberOfDefects)
        {
            plane.numberOfTechnicians =  NUMBER_OF_TECHS;
            plane.autoAssignTechnicians();

            Intent intent = new Intent(SupervisorPlaneDetail.this, SupervisorMain.class);
            startActivity(intent);
        }
        else
        {
            //error message because too many technicians are assigned
            number_of_techs.setText("");
        }
    }

    public void manualAssignButton(View view)
    {
        NUMBER_OF_TECHS = Integer.parseInt(number_of_techs.getText().toString());
        if (NUMBER_OF_TECHS <= plane.numberOfDefects)
        {
            Intent intent = new Intent(SupervisorPlaneDetail.this, SupervisorPriorityTask.class);
            intent.putExtra("NUMBER_OF_TECHS", NUMBER_OF_TECHS);
            intent.putExtra("PLANE_ID", plane_ID);
            startActivity(intent);
        }
        else
        {
            //error message because too many technicians are assigned
            number_of_techs.setText("");
        }
    }

    public void backButton(View view)
    {
        Intent intent = new Intent(SupervisorPlaneDetail.this, SupervisorMain.class);
        startActivity(intent);
    }
}
