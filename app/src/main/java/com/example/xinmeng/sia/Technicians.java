package com.example.xinmeng.sia;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Xin Meng on 23/10/2017.
 */

public class Technicians {
    public Queue planes; //all planes assigned to this technician
    public Queue allTasks; //all tasks in the planes queue that are assigned to this technician
    public String ID; // ID of this technician
    public List currentTasks = new ArrayList<Defects>(); // current tasks in current plane that is in progress
    public boolean onTask; // checks whether the technician has any active tasks
    public boolean onPlane; // checks whether the technician is handling any active planes
    //KPI

    public void addTasks(Plane plane)
    {
        planes.add(plane);
        for (Object child : plane.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.techID == ID)
                allTasks.add(newChild);
        }
    }

    public void boardPlane()
    {
        ((Plane) planes.element()).inProgress = true;
        onPlane = true;
    }

    public void startTask (Defects task)
    {
        int n = currentTasks.indexOf(task);
    }

    public void completeTasks()
    {
        planes.remove();
    }

    public void setCurrentTasks()
    {
        Plane currentPlane = (Plane) planes.element(); //next plane in the queue
        if (currentPlane != null && currentPlane.inProgress)
        {
            for (Object child : currentPlane.defects)
            {
                Defects newChild = (Defects) child;
                if (newChild.techID == ID)
                    currentTasks.add(newChild);
            }
        }
    }

    public Defects currentTask()
    {
        for (Object child : currentTasks)
        {
            Defects newChild = (Defects) child;
            if (newChild.inProgress)
                return newChild;
        }
        return null;
    }

    public void doTask(Defects task)
    {
        int n = currentTasks.indexOf(task);
    }
}
