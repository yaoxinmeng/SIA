package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class SupervisorMain extends AppCompatActivity {
    private final int entryNumber = 5; //number of entries displayed per page
    private int page = 1; //page number
    private boolean unassigned;
    private boolean assigned;
    private boolean inProgress;
    private boolean completed;
    private List planeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_main);

        unassigned = true;
        assigned = false;
        inProgress = false;
        completed = false;

        update();
    }

    protected void onResume(Bundle savedInstanceState)
    {
        update();
    }

    private void update()
    {
        while (true)
        {
            //whatever that needs to be updated
            if (unassigned)
                planeDisplay = cutList(planesUnassigned(), page);
            else if (assigned)
                planeDisplay = cutList(planesAssigned(), page);
            else if (inProgress)
                planeDisplay = cutList(planesInProgress(), page);
            else
                planeDisplay = cutList(planesCompleted(), page);

            //transfers planeDisplay to buttons
            for (Object child : planeDisplay)
            {

            }

            //waits 10 sec before next loop
            SystemClock.sleep(10000);
        }
    }

    private List planesUnassigned()
    {
        List allUnassignedPlanes = null;

        //Adds unassigned planes to list
        for (Object child : Database.planes)
        {
            Plane plane = (Plane) child;
            if (!plane.assigned)
                allUnassignedPlanes.add(plane);
        }

        return sortedList(allUnassignedPlanes);
    }

    private List planesAssigned()
    {
        List allAssignedPlanes = null;

        //Adds unassigned planes to list
        for (Object child : Database.planes)
        {
            Plane plane = (Plane) child;
            if (plane.assigned && !plane.inProgress)
                allAssignedPlanes.add(plane);
        }

        return sortedList(allAssignedPlanes);
    }

    private List planesInProgress()
    {
        List allInProgressPlanes = null;

        //Adds unassigned planes to list
        for (Object child : Database.planes)
        {
            Plane plane = (Plane) child;
            if (plane.inProgress && !plane.completed)
                allInProgressPlanes.add(plane);
        }

        return sortedList(allInProgressPlanes);
    }

    private List planesCompleted()
    {
        List allCompletedPlanes = null;

        //Adds unassigned planes to list
        for (Object child : Database.planes)
        {
            Plane plane = (Plane) child;
            if (plane.completed)
                allCompletedPlanes.add(plane);
        }

        return sortedList(allCompletedPlanes);
    }

    private List sortedList (List planes)
    {
        if (planes == null)
            return null;
        else
        {
            List tempList = new ArrayList<Plane>();

            //Sorts in tempList
            for (Object child : planes)
            {
                Plane plane = (Plane) child;
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

    private List cutList(List planes, int page)
    {
        List tempList = null;
        int n = (page - 1) * entryNumber + 1;

        if (planes.size() < n)
            return null;

        for (int x = n - 1; x < planes.size(); x++)
        {
            tempList.add(planes.get(n));
        }
        return tempList;
    }

    //Button functions
    public void nextPage(View view) //for next page button
    {
        page++;
        onResume();
    }

    public void prevPage(View view) //for previous page button
    {
        if (page != 1)
            page--;
        onResume();
    }

    public void entry1(View view)
    {
        Plane plane = (Plane) Database.planes.get(page - 1);
    }

    public void entry2(View view)
    {
        Plane plane = (Plane) Database.planes.get(2 * page - 1);
    }

    public void entry3(View view)
    {
        Plane plane = (Plane) Database.planes.get(3 * page - 1);
    }

    public void entry4(View view)
    {
        Plane plane = (Plane) Database.planes.get(4 * page - 1);
    }

    public void entry5(View view)
    {
        Plane plane = (Plane) Database.planes.get(5 * page - 1);
    }

    public void unassignedTab(View view)
    {

    }
}
