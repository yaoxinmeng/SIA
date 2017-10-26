package com.example.xinmeng.sia;

import java.util.Date;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Defects {
    public String category;
    public String description;
    public String regn;
    public String dateRaised;
    public String fleet;
    public String stn;
    public String number;
    public int classCode;

    public boolean inProgress;
    public boolean resolved;

    //Assigned by planners/supervisors
    public String parts;
    public String action;
    public int priority;
    public String deferralReason;
    public String techID; //techID assigned to this defect

    public Defects(String category, String description, String regn, String dateRaised, String fleet, String stn, String number, String classCode) {
        this.category = category;
        this.description = description;
        this.regn = regn;
        this.dateRaised = dateRaised;
        this.fleet = fleet;
        this.stn = stn;
        this.number = number;

        if (classCode.equals("Economy"))
            this.classCode = Database.Economy;
        else if (classCode.equals("Premium"))
            this.classCode = Database.Premium;
        else if (classCode.equals("Business"))
            this.classCode = Database.Business;
        else if (classCode.equals("First"))
            this.classCode = Database.First;

        this.inProgress = false;
        this.resolved = false;
        this.parts = null;
        this.action = null;
        this.priority = 0;
        this.deferralReason = null;
        this.techID = null;
    }
}
