package com.example.xinmeng.sia;

/**
 * Created by jrnew on 26/10/2017.
 */

public class TechnicianData {
    private String planeID;
    private String id;

    public TechnicianData(){}
    public TechnicianData(Technicians technicians){
        technicians.updateIDs();
        this.planeID = technicians.planeID;
        this.id = technicians.ID;
    }
    public TechnicianData(String planeID, String id){
        this.planeID = planeID;
        this.id = id;
    }

    @com.google.gson.annotations.SerializedName("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @com.google.gson.annotations.SerializedName("planeID")
    public String getPlaneID(){
        return planeID;
    }
    public void setPlaneID(String planeID){
        this.planeID = planeID;
    }

}
