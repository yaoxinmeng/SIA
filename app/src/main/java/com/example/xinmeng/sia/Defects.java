package com.example.xinmeng.sia;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Defects {
    public String category;
    public String description;
    public String regn;
    public int classCode;

    public boolean inProgress;
    public boolean resolved;

    //Assigned by planners/supervisors
    public String parts;
    public String action;
    public int priority;

    public String techID; //techID assigned to this defect

    public Defects(String category, String description, String regn, int classCode) {
        this.category = category;
        this.description = description;
        this.regn = regn;
        this.classCode = classCode;
        this.inProgress = false;
        this.resolved = true;
    }
}
