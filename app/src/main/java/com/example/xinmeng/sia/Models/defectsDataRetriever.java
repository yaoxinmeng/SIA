package com.example.xinmeng.sia.Models;

import android.content.Context;
import android.widget.Toast;

import com.example.xinmeng.sia.DefectsFetcher;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jrnew on 26/10/2017.
 * Guide to use: initialise defectsDataRetriever dataGetter = new defectsDataRetriever(this);
 * Then call to get the defects: List of defectsFetcher result = dataGetter.fetchDefectsData();
 */

public class defectsDataRetriever {
    MobileServiceClient mClient;
    MobileServiceTable<DefectsFetcher> mDefectsTable;
    public defectsDataRetriever(Context context) {
        try {
            mClient = new MobileServiceClient("https://defectslist.azurewebsites.net/", context);
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });
            mDefectsTable = mClient.getTable("DefectsData", DefectsFetcher.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<DefectsFetcher> fetchDefectsData(){
        List<DefectsFetcher> data = new ArrayList<DefectsFetcher>();
        try {
            final List<DefectsFetcher> finalData = data;
            mDefectsTable.execute(new TableQueryCallback<DefectsFetcher>(){
                public void onCompleted(List<DefectsFetcher> result, int count, Exception exception,
                                        ServiceFilterResponse response) {
                    if(exception == null){
                        for (DefectsFetcher d : result) {
                            finalData.add(d);
                        }
                    }
                }
            });
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
        return data;

    }

}
