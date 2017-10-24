package com.example.xinmeng.sia;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Defects {
    public String category;
    public String description;
    public String flightNumber;
    public int classCode;

    public boolean assigned;
    public boolean inProgress;
    public boolean resolved;

    //Assigned by planners/supervisors
    public String parts;
    public String action;
    public int priority;

    public String techID; //techID assigned to this defect

    public Defects(String category, String description, String flightNumber, int classCode) {
        this.category = category;
        this.description = description;
        this.flightNumber = flightNumber;
        this.classCode = classCode;
        this.assigned = false;
        this.inProgress = false;
        this.resolved = true;
    }
}
