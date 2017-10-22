package com.example.xinmeng.sia;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Plane {
    public String location;
    public float landingTime;
    public float takeoffTIme;
    public int flightNumber;
    public List defects = new ArrayList<Defects>();

    Plane(String location, float landingTime, float takeoffTIme, int flightNumber)
    {
        this.location = location;
        this.landingTime = landingTime;
        this.takeoffTIme = takeoffTIme;
        this.flightNumber = flightNumber;

        for (Object child : Database.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.flightNumber == flightNumber)
                defects.add(newChild);
        }
        this.defects = defects;
    }
}
