package com.example.xinmeng.sia;

import java.io.IOException;
import java.io.ObjectStreamException;
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
    public int age;
    public int ata;

    public boolean inProgress;
    public boolean resolved;

    //Assigned by planners/supervisors
    public String parts;
    public String action;
    public int priority;
    public String deferralReason;
    public String techID; //techID assigned to this defect

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

    public Defects(){}
    public Defects(String category, String description, String regn, String dateRaised, String fleet, String stn, String number, String classCode, int age, int ata) {
        this.category = category;
        this.description = description;
        this.regn = regn;
        this.dateRaised = dateRaised;
        this.fleet = fleet;
        this.stn = stn;
        this.number = number;
        this.age = age;
        this.ata = ata;

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

    public Defects (DefectsFetcher defect)
    {
        this.category = defect.getCategory();
        this.description = defect.getDefects();
        this.regn = defect.getRegn();
        this.dateRaised = defect.getDateRaised();
        this.fleet = defect.getFleet();
        this.stn = defect.getStn();
        this.number = defect.getId();
        this.age = defect.getAgeing();
        this.ata = defect.getAta();

        if (defect.getClassCode().equals("Economy"))
            this.classCode = Database.Economy;
        else if (defect.getClassCode().equals("Premium"))
            this.classCode = Database.Premium;
        else if (defect.getClassCode().equals("Business"))
            this.classCode = Database.Business;
        else if (defect.getClassCode().equals("First"))
            this.classCode = Database.First;

        this.inProgress = defect.getInProgress();
        this.resolved = defect.getResolved();
        this.parts = defect.getPartDetails();
        this.action = defect.getAction();
        this.priority = 0;
        this.deferralReason = defect.getDeferralReason();
        this.techID = defect.getTechnicianID();
    }
}
