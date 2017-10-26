package com.example.xinmeng.sia;

import android.content.Context;

import com.example.xinmeng.sia.ViewHolders.PlaneData;
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
 */

public class PlaneDataRetriever {
//    MobileServiceClient mClient;
    MobileServiceTable<PlaneData> mPlanesTable;
    public PlaneDataRetriever(MobileServiceClient mClient) {
        mPlanesTable = mClient.getTable("PlanesData", PlaneData.class);
//        try {
//            mClient = new MobileServiceClient("https://sactest.azurewebsites.net", context);
//            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
//                @Override
//                public OkHttpClient createOkHttpClient() {
//                    OkHttpClient client = new OkHttpClient();
//                    client.setReadTimeout(20, TimeUnit.SECONDS);
//                    client.setWriteTimeout(20, TimeUnit.SECONDS);
//                    return client;
//                }
//            });
//            mPlanesTable = mClient.getTable("PlanesData", PlaneData.class);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    public List<PlaneData> fetchPlanesData(){
        List<PlaneData> data = new ArrayList<PlaneData>();
        try {
            final List<PlaneData> finalData = data;
            mPlanesTable.execute(new TableQueryCallback<PlaneData>(){
                public void onCompleted(List<PlaneData> result, int count, Exception exception,
                                        ServiceFilterResponse response) {
                    if(exception == null){
                        for (PlaneData d : result) {
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

    public boolean updateData(PlaneData item){
        if(mPlanesTable.update(item)!=null)
            return true;
        else return false;
    }

    public boolean insertData(PlaneData item){
        if(mPlanesTable.insert(item)!=null)
            return true;
        else return false;
    }
}
