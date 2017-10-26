package com.example.xinmeng.sia;

import com.example.xinmeng.sia.Models.defectsDataRetriever;
import com.example.xinmeng.sia.ViewHolders.PlaneData;

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

    //helper classes to get the required data
    private static List<DefectsFetcher> defectsFetcher = new ArrayList<DefectsFetcher>();
    private static List<PlaneData> planesData = new ArrayList<PlaneData>();

    public static void updateFromDatabase()
    {
        defectsFetcher = databaseGetter.getInstance().getDefectsDataGetter().fetchDefectsData();
        planesData = databaseGetter.getInstance().getPlaneDataGetter().fetchPlanesData();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    if(!defectsFetcher.isEmpty()){
                        defects.clear();
                        for(DefectsFetcher d : defectsFetcher)
                            defects.add(new Defects(d));
                    }
                    if (!planesData.isEmpty()){
                        planes.clear();
                        for(PlaneData p : planesData)
                            planes.add(new Plane(p));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    public static void updateToDatabase(Plane plane)
    {
        //push to jinrui's database
        databaseGetter.getInstance().getPlaneDataGetter().updateData(new PlaneData(plane));

    }

    public static void updateToDatabase(Defects defect)
    {
        //push to jinrui's database
        databaseGetter.getInstance().getDefectsDataGetter().updateData(new DefectsFetcher(defect));
    }

    public static void updateToDatabase(Technicians tech)
    {
        //push to jinrui's database

    }
}
