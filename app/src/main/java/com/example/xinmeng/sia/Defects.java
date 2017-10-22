package com.example.xinmeng.sia;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Defects {
    public String name;
    public String description;
    public int seatNumber;
    public int flightNumber;

    //Assigned by planners/supervisors
    public String equipment;
    public int priority;
    public String techID;

    public Defects(String name, String description, int seatNumber, int flightNumber)
    {
        this.name = name;
        this.description = description;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
    }
}
