package com.example.xinmeng.sia;


import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Plane {
    public String location;
    public Date landingTime;
    public Date takeoffTIme;
    public long timeLeft; //needs to be updated in activity
    public int flightNumber;
    public List defects = new ArrayList<Defects>();
    public int numberOfDefects;
    public int numberOfTechnicians;
    public boolean assigned;
    public boolean inProgress;
    public boolean completed;

    Plane(String location, Date landingTime, Date takeoffTIme, int flightNumber)
    {
        this.location = location;
        this.landingTime = landingTime;
        this.takeoffTIme = takeoffTIme;
        this.timeLeft = takeoffTIme.getTime() - currentTimeMillis();
        this.flightNumber = flightNumber;

        for (Object child : Database.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.flightNumber == flightNumber)
                this.defects.add(newChild);
        }
        this.numberOfDefects = this.defects.size();
    }

    public void assignTechnicians()
    {
        List technicians = new ArrayList<Technicians>();
        for(Object child : Database.technicians)
        {
            Technicians newChild = (Technicians) child;


        }
    }
}
