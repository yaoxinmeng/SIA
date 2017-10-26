package com.example.xinmeng.sia.ViewHolders;


import com.example.xinmeng.sia.Plane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by jrnew on 26/10/2017.
 */

public class PlaneData {
    private String id; //id is the regn. need to name it id because table needs an id as unique identifier
    private String ACType;
    private int in;
    private int out;
    private String arrDate;
    private String arrTime;
    private String depDate;
    private String depTime;
    private boolean delay;
    private String bay;

    public PlaneData(String id, String ACType, int in, int out, String arrDate, String arrTime, String depDate, String depTime, boolean delay, String bay){
        this.id=id;
        this.ACType = ACType;
        this.in = in;
        this.out = out;
        this.arrTime = arrTime;
        this.arrDate = arrDate;
        this.depTime = depTime;
        this.depDate = depDate;
        this.delay= delay;
        this.bay = bay;
    }
    public PlaneData(Plane plane){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        DateFormat tf = new SimpleDateFormat("HH:mm");
        this.id = plane.regn;
        this.ACType = plane.type;
        this.arrDate = df.format(plane.arrTime);
        this.depDate = df.format(plane.depTIme);
        this.arrTime = tf.format(plane.arrTime);
        this.depTime = tf.format(plane.depTIme);
        this.delay = plane.delay;
        this.bay = plane.bay;
    }


    @com.google.gson.annotations.SerializedName("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @com.google.gson.annotations.SerializedName("ACType")
    public String getACType() {
        return ACType;
    }

    public void setACType(String ACType) {
        this.ACType = ACType;
    }

    @com.google.gson.annotations.SerializedName("in")
    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    @com.google.gson.annotations.SerializedName("out")
    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    @com.google.gson.annotations.SerializedName("arrTime")
    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    @com.google.gson.annotations.SerializedName("depTime")
    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    @com.google.gson.annotations.SerializedName("arrDate")
    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    @com.google.gson.annotations.SerializedName("depDate")
    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    @com.google.gson.annotations.SerializedName("delay")
    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    @com.google.gson.annotations.SerializedName("bay")
    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }
}
