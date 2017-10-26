package com.example.xinmeng.sia;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Xin Meng on 23/10/2017.
 */

public class Technicians {
    public Deque planes; //all planes assigned to this technician
    public Deque allTasks; //all tasks in the planes queue that are assigned to this technician
    public Plane currentPlane;
    public String ID; // ID of this technician
    public List currentTasks = new ArrayList<Defects>(); // current tasks in current plane that is in progress
    public int numberOfTasks; //number of non-completed tasks in allTasks

    public Technicians() {
        planes = null;
        allTasks = null;
    }

    public void refresh()
    {
        setNumberOfTasks();
    }

    public void setNumberOfTasks()
    {
        int n = 0;
        for(Object child : allTasks)
        {
            if (!((Defects)child).resolved)
                n++;
        }
        numberOfTasks = n;
    }

    public void addPriorityPlane (Plane plane)
    {
        planes.addFirst(plane);
        plane.assigned = true;
        for (Object child : plane.defects)
        {
            Defects newChild = (Defects) child;
            allTasks.addFirst(newChild);
        }
        numberOfTasks = allTasks.size();
    }

    public void addPlane(Plane plane)
    {
        planes.add(plane);
        plane.assigned = true;
        for (Object child : plane.defects)
        {
            Defects newChild = (Defects) child;
            allTasks.add(newChild);
        }
        numberOfTasks = allTasks.size();
    }

    public void boardPlane() {
        currentPlane = (Plane) planes.element();
        planes.removeFirst();
        currentPlane.inProgress = true;

        //sets current tasks
        for (Object child : currentPlane.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.techID.equals(ID))
                currentTasks.add(newChild);
        }
    }

    public boolean allTasksCompleted()
    {
        boolean allTasksCompleted = true;

        //checks if all tasks on this plane have been completed
        for (Object child : ((Plane) planes.element()).defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.techID.equals(ID) && !newChild.resolved)
                allTasksCompleted = false;
        }

        return allTasksCompleted;
    }

    public void leavePlane()
    {
        //removes tasks of this plane from allTasks
        for (Object child : allTasks)
        {
            Defects newChild = (Defects) child;
            if (newChild.regn.equals(((Plane) planes.element()).regn))
                allTasks.remove(newChild);
        }
        currentPlane = null;
        currentTasks.clear();

        boardPlane();
    }

    public void startTask (Defects task)
    {
        task.inProgress = true;
    }

    public void resolvedTask (Defects task)
    {
        task.resolved = true;
    }

    public void unresolvedTask (Defects task)
    {
        task.resolved = false;
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

    public List tasksArchive()
    {
        List tasksArchive = new ArrayList<Defects>();
        for (Object child : Database.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.resolved && newChild.techID.equals(this.ID))
                tasksArchive.add(newChild);
        }
        return tasksArchive;
    }
}
