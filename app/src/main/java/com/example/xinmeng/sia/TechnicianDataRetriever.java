package com.example.xinmeng.sia;

import com.example.xinmeng.sia.ViewHolders.PlaneData;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrnew on 26/10/2017.
 */

public class TechnicianDataRetriever {

    //    MobileServiceClient mClient;
    MobileServiceTable<TechnicianData> mTechniciansTable;
    public TechnicianDataRetriever(MobileServiceClient mClient) {
        mTechniciansTable = mClient.getTable("TechniciansData", TechnicianData.class);
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
//            mPlanesTable = mClient.getTable("TechniciansData", TechnicianData.class);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    public List<TechnicianData> fetchTechniciansData(){
        List<TechnicianData> data = new ArrayList<TechnicianData>();
        try {
            final List<TechnicianData> finalData = data;
            mTechniciansTable.execute(new TableQueryCallback<TechnicianData>(){
                public void onCompleted(List<TechnicianData> result, int count, Exception exception,
                                        ServiceFilterResponse response) {
                    if(exception == null){
                        for (TechnicianData d : result) {
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

    public boolean updateData(TechnicianData item){
        if(mTechniciansTable.update(item)!=null)
            return true;
        else return false;
    }

    public boolean updateData(Technicians item){
        TechnicianData newItem = new TechnicianData(item);
        return updateData(newItem);
    }

    public boolean insertData(TechnicianData item){
        if(mTechniciansTable.insert(item)!=null)
            return true;
        else return false;
    }

    public boolean insertData(Technicians item){
        TechnicianData newItem = new TechnicianData(item);
        return insertData(newItem);
    }
}

