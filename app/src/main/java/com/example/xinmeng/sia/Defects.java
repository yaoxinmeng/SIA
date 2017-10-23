package com.example.xinmeng.sia;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Defects {
    public String category;
    public String description;
    public String flightNumber;
    public int classCode;

    //definitions for classCode
    private static final int Economy = 0;
    private static final int Premium = 1;
    private static final int Business = 2;
    private static final int First = 3;

    public boolean assigned;
    public boolean inProgress;
    public boolean completed;
    public boolean resolved;

    //Assigned by planners/supervisors
    public String parts;
    public String action;
    public int priority;

    //Auto assigned
    public String techID;

    public Defects(String category, String description, String flightNumber) {
        this.category = category;
        this.description = description;
        this.flightNumber = flightNumber;
        this.assigned = false;
        this.inProgress = false;
        this.completed = false;
        this.resolved = true;
    }
}
