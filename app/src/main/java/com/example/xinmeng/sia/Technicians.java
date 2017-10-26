package com.example.xinmeng.sia;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * Created by Xin Meng on 23/10/2017.
 */

public class Technicians {
    public String planeID;
    public Deque<Plane> planes; //all planes assigned to this technician
    public Deque<Defects> allTasks; //all tasks in the planes queue that are assigned to this technician
    public Plane currentPlane;
    public String ID; // ID of this technician
    public int numberOfTasks; //number of non-completed tasks in allTasks

    //For serialization
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {

    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {

    }

    private void readObjectNoData()
            throws ObjectStreamException {

    }

    public Technicians() {
        planeID = "";
        planes = null;
        allTasks = null;
        currentPlane = null;
        ID = null;
        numberOfTasks = 0;
    }

    public Technicians(TechnicianData technicianData){
        planeID = technicianData.getPlaneID();
        ID = technicianData.getId();
        this.updatePlanes();
    }

    public void refresh()
    {
        setNumberOfTasks();
    }

    public void updatePlanes()
    {
        String[] planeIDs = ID.split("-");
        for (String ID : planeIDs)
        {
            for (Plane plane : Database.planes)
            {
                if (plane.regn.equals(ID))
                    planes.add(plane);
            }
        }
    }

    public void updateIDs()
    {
        planeID = "";
        if (planes!=null) {
            for (Plane plane : planes) {
                planeID = plane.regn + "-";
            }
        }
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
        updateIDs();
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
        updateIDs();
    }

    public void boardPlane() {
        currentPlane = (Plane) planes.element();
        planes.removeFirst();
        currentPlane.inProgress = true;
        updateIDs();
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
        for (Defects child : currentPlane.defects)
        {
            if (child.inProgress)
                return child;
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
