package com.example.xinmeng.sia;

import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import static java.lang.System.currentTimeMillis;

public class SupervisorMain extends AppCompatActivity {
    private final int entryNumber = 5; //number of entries displayed per page
    private int page = 1; //page number
    private int maxPage; //max page number possible
    private boolean unassigned;
    private boolean assigned;
    private boolean inProgress;
    private boolean completed;
    private ArrayList<Plane> planeDisplay = new ArrayList<Plane>();

    TextView[] regnDisp= new TextView[5];
    TextView[] arrTimeDisp= new TextView[5];
    TextView[] depTimeDisp = new TextView[5];
    TextView[] numberDefectsDisp = new TextView[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_main);

        Database.updateFromDatabase();
        unassigned = true;
        assigned = false;
        inProgress = false;
        completed = false;
        maxPage = (int) (Database.planes.size() / entryNumber) + 1;
        regnDisp[0] = (TextView) findViewById(R.id.ac_table_1_1);
        regnDisp[1] = (TextView) findViewById(R.id.ac_table_2_1);
        regnDisp[2] = (TextView) findViewById(R.id.ac_table_3_1);
        regnDisp[3] = (TextView) findViewById(R.id.ac_table_4_1);
        regnDisp[4] = (TextView) findViewById(R.id.ac_table_5_1);
        arrTimeDisp[0] = (TextView) findViewById(R.id.ac_table_1_2);
        arrTimeDisp[1] = (TextView) findViewById(R.id.ac_table_2_2);
        arrTimeDisp[2] = (TextView) findViewById(R.id.ac_table_3_2);
        arrTimeDisp[3] = (TextView) findViewById(R.id.ac_table_4_2);
        arrTimeDisp[4] = (TextView) findViewById(R.id.ac_table_5_2);
        depTimeDisp[0] = (TextView) findViewById(R.id.ac_table_1_3);
        depTimeDisp[1] = (TextView) findViewById(R.id.ac_table_2_3);
        depTimeDisp[2] = (TextView) findViewById(R.id.ac_table_3_3);
        depTimeDisp[3] = (TextView) findViewById(R.id.ac_table_4_3);
        depTimeDisp[4] = (TextView) findViewById(R.id.ac_table_5_3);
        numberDefectsDisp[0] = (TextView) findViewById(R.id.ac_table_1_4);
        numberDefectsDisp[1] = (TextView) findViewById(R.id.ac_table_2_4);
        numberDefectsDisp[2] = (TextView) findViewById(R.id.ac_table_3_4);
        numberDefectsDisp[3] = (TextView) findViewById(R.id.ac_table_4_4);
        numberDefectsDisp[4] = (TextView) findViewById(R.id.ac_table_5_4);
        update();
    }

    protected void onResume(Bundle savedInstanceState)
    {
        super.onResume();
        update();
    }

    private void update()
    {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //whatever that needs to be updated
                    setPlaneDisplay();
                    for (Plane child : planeDisplay) {
                        child.timeLeft = child.depTIme.getTime() - currentTimeMillis();
                    }
                    Thread.sleep(10000);
                    Database.updateFromDatabase();

                    //waits 10 sec before next loop
                    update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    //Generic functions
    private ArrayList<Plane> planesUnassigned()
    {
        ArrayList<Plane> allUnassignedPlanes = new ArrayList<>();

        //Adds unassigned planes to list
        for (Plane plane : Database.planes)
        {
            if (!plane.assigned)
                allUnassignedPlanes.add(plane);
        }

        return sortedList(allUnassignedPlanes);
    }

    private ArrayList<Plane> planesAssigned()
    {
        ArrayList<Plane> allAssignedPlanes = new ArrayList<>();

        //Adds unassigned planes to list
        for (Plane plane : Database.planes)
        {
            if (plane.assigned && !plane.inProgress)
                allAssignedPlanes.add(plane);
        }

        return sortedList(allAssignedPlanes);
    }

    private ArrayList<Plane> planesInProgress()
    {
        ArrayList<Plane> allInProgressPlanes = new ArrayList<>();

        //Adds unassigned planes to list
        for (Plane plane : Database.planes)
        {
            if (plane.inProgress && !plane.completed)
                allInProgressPlanes.add(plane);
        }

        return sortedList(allInProgressPlanes);
    }

    private ArrayList<Plane> planesCompleted()
    {
        ArrayList<Plane> allCompletedPlanes = null;

        //Adds unassigned planes to list
        for (Plane plane : Database.planes)
        {
            if (plane.completed)
                allCompletedPlanes.add(plane);
        }

        return sortedList(allCompletedPlanes);
    }

    private ArrayList<Plane> sortedList (ArrayList<Plane> planes)
    {
        if (planes == null)
            return null;
        else
        {
            ArrayList<Plane> tempList = new ArrayList<>();

            //Sorts in tempList
            for (Plane plane : planes)
            {
                if (tempList.size() == 0)
                    tempList.add(plane);
                else
                {
                    for (int n = 0; n < tempList.size(); n++)
                    {
                        if(plane.depTIme.getTime() < ((Plane) tempList.get(n)).depTIme.getTime())
                        {
                            tempList.add(n, plane);
                            break;
                        }
                    }
                }
            }
            return tempList;
        }
    }

    private ArrayList<Plane> cutList(ArrayList<Plane> planes, int page)
    {
        ArrayList<Plane> tempList = new ArrayList<>();

        int n = (page - 1) * entryNumber + 1;
        if (planes.size() < n)
        {
            page--;
            checkPage();
            return null;
        }

        for (int x = n - 1; x < n + entryNumber - 1; x++)
        {
            if (planes.get(x) == null)
                break;
            tempList.add(n, planes.get(x));
        }
        return tempList;
    }

    private void checkPage()
    {
        page=1;
        if (unassigned)
            planeDisplay = cutList(planesUnassigned(), page);
        else if (assigned)
            planeDisplay = cutList(planesAssigned(), page);
        else if (inProgress)
            planeDisplay = cutList(planesInProgress(), page);
        else
            planeDisplay = cutList(planesCompleted(), page);

        //transfers planeDisplay to buttons
        for (Plane plane : planeDisplay)
        {


        }
    }

    //On-Page button functions
    public void nextPage(View view) //for next page button
    {
        page++;
        checkPage();
    }

    public void prevPage(View view) //for previous page button
    {
        if (page != 1)
            page--;
        checkPage();
    }

    public void entry1(View view)
    {
        if (planeDisplay != null)
        {
            Plane plane = planeDisplay.get(0);
            if (plane != null)
            {
                Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
                intent.putExtra("PLANE_ID", plane.regn);
                startActivity(intent);
            }
        }
    }

    public void entry2(View view)
    {
        if (planeDisplay != null)
        {
            Plane plane = planeDisplay.get(1);
            if (plane != null)
            {
                Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
                intent.putExtra("PLANE_ID", plane.regn);
                startActivity(intent);
            }
        }
    }

    public void entry3(View view)
    {
        if (planeDisplay != null)
        {
            Plane plane = planeDisplay.get(2);
            if (plane != null)
            {
                Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
                intent.putExtra("PLANE_ID", plane.regn);
                startActivity(intent);
            }
        }
    }

    public void entry4(View view)
    {
        if (planeDisplay != null)
        {
            Plane plane = planeDisplay.get(3);
            if (plane != null)
            {
                Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
                intent.putExtra("PLANE_ID", plane.regn);
                startActivity(intent);
            }
        }
    }

    public void entry5(View view)
    {
        if (planeDisplay != null)
        {
            Plane plane = planeDisplay.get(4);
            if (plane != null)
            {
                Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
                intent.putExtra("PLANE_ID", plane.regn);
                startActivity(intent);
            }
        }
    }

    //Tabs function
    public void unassignedTab(View view)
    {
        unassigned = true;
        assigned = false;
        inProgress = false;
        completed = false;
        checkPage();
    }

    public void assignedTab(View view)
    {
        unassigned = false;
        assigned = true;
        inProgress = false;
        completed = false;
        checkPage();
    }

    public void inProgressTab(View view)
    {
        unassigned = false;
        assigned = true;
        inProgress = false;
        completed = false;
        checkPage();
    }

    public void completedTab(View view)
    {
        unassigned = false;
        assigned = false;
        inProgress = false;
        completed = true;
        checkPage();
    }

    public void setPlaneDisplay()
    {
        List<Plane> planeList = Database.planes;
        DateFormat tf = new SimpleDateFormat("HH:mm");
        for (int i = 0; i< ((5<planeList.size())? 5 : planeList.size()); i++){
            regnDisp[i].setText(planeList.get(i).regn);
            arrTimeDisp[i].setText(tf.format(planeList.get(i).arrTime));
            depTimeDisp[i].setText(tf.format(planeList.get(i).depTIme));
            numberDefectsDisp[i].setText(Integer.toString(planeList.get(i).numberOfDefects));
        }
    }

}


