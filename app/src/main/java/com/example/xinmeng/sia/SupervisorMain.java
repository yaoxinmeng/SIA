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
    private ArrayList<Plane> planeDisplay;

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

        update();
    }

    protected void onResume(Bundle savedInstanceState)
    {
        super.onResume();
        update();
    }

    private void update()
    {
        while (true)
        {
            //whatever that needs to be updated
            for (Plane child : planeDisplay)
            {
                child.timeLeft = child.depTIme.getTime() - currentTimeMillis();
            }

            Database.updateFromDatabase();

            //waits 10 sec before next loop
            SystemClock.sleep(10000);
        }
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
        Plane plane = planeDisplay.get(0);
        if (plane != null)
        {
            Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
            intent.putExtra("PLANE_ID", plane.regn);
            startActivity(intent);
        }
    }

    public void entry2(View view)
    {
        Plane plane = planeDisplay.get(1);
        if (plane != null)
        {
            Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
            intent.putExtra("PLANE_ID", plane.regn);
            startActivity(intent);
        }
    }

    public void entry3(View view)
    {
        Plane plane = planeDisplay.get(2);
        if (plane != null)
        {
            Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
            intent.putExtra("PLANE_ID", plane.regn);
            startActivity(intent);
        }
    }

    public void entry4(View view)
    {
        Plane plane = planeDisplay.get(3);
        if (plane != null)
        {
            Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
            intent.putExtra("PLANE_ID", plane.regn);
            startActivity(intent);
        }
    }

    public void entry5(View view)
    {
        Plane plane = planeDisplay.get(4);
        if (plane != null)
        {
            Intent intent = new Intent(SupervisorMain.this, SupervisorPlaneDetail.class);
            intent.putExtra("PLANE_ID", plane.regn);
            startActivity(intent);
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
}


