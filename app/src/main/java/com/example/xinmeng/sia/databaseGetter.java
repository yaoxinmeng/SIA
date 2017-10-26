package com.example.xinmeng.sia;

import android.content.Context;

import com.example.xinmeng.sia.Models.defectsDataRetriever;

/**
 * Created by jrnew on 26/10/2017.
 */

public class databaseGetter {
    private defectsDataRetriever defectsDataGetter;
    private PlaneDataRetriever planeDataGetter;
    private static databaseGetter ourInstance;
    private static Context context=null;
    public static void initialise(Context ctx){
        context = ctx;
    }

    public static boolean hasBeenInitialised(){
        return context != null;
    }

    public static databaseGetter getInstance() {
        if (context == null) {
        throw new IllegalArgumentException("Impossible to get the instance. This class must be initialized before");
    }

        if (ourInstance == null) {
            ourInstance = new databaseGetter();
        }

        return ourInstance;
    }

    public defectsDataRetriever getDefectsDataGetter()
    {
        return defectsDataGetter;
    }

    public PlaneDataRetriever getPlaneDataGetter()
    {
        return planeDataGetter;
    }

    private databaseGetter() {
        planeDataGetter = new PlaneDataRetriever(context);
        defectsDataGetter = new defectsDataRetriever(context);
    }
}
