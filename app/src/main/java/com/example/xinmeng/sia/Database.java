package com.example.xinmeng.sia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Database {
    public static List<Defects> defects = new ArrayList<Defects>();
    public static List<Plane> planes = new ArrayList<Plane>();
    public static List<Technicians> technicians = new ArrayList<Technicians>();
    public static List<String> techIDs = new ArrayList<String>();
    public static List<String> supervisorIDs = new ArrayList<String>();
    public static List<String> plannerIDs = new ArrayList<String>();

    //definitions for classCode
    public static final int Economy = 0;
    public static final int Premium = 1;
    public static final int Business = 2;
    public static final int First = 3;

    public static void updateFromDatabase()
    {
        //pull from jinrui's database

    }

    public static void updateToDatabase(Plane plane)
    {
        //push to jinrui's database

    }

    public static void updateToDatabase(Defects defect)
    {
        //push to jinrui's database

    }

    public static void updateToDatabase(Technicians tech)
    {
        //push to jinrui's database

    }
}
