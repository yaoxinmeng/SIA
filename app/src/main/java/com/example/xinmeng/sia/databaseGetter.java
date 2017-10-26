package com.example.xinmeng.sia;

import android.content.Context;

import com.example.xinmeng.sia.Models.defectsDataRetriever;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jrnew on 26/10/2017.
 */

public class databaseGetter {
    private defectsDataRetriever defectsDataGetter;
    private PlaneDataRetriever planeDataGetter;
    private TechnicianDataRetriever technicianDataGetter;
    private static databaseGetter ourInstance;
    private static Context context=null;
    private static MobileServiceClient mClient;
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

    public TechnicianDataRetriever getTechnicianDataGetter()
    {
        return technicianDataGetter;
    }

    private databaseGetter() {
        try {
            mClient = new MobileServiceClient("https://sactest.azurewebsites.net", context);
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        planeDataGetter = new PlaneDataRetriever(mClient);
        defectsDataGetter = new defectsDataRetriever(mClient);
        technicianDataGetter = new TechnicianDataRetriever(mClient);
    }
}
