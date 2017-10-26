package com.example.xinmeng.sia;


import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.example.xinmeng.sia.ViewHolders.PlaneData;

import java.io.ObjectStreamException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setErr;

/**
 * Created by Xin Meng on 22/10/2017.
 */

public class Plane {
    //Aircraft Ground TIme
    public String bay;
    public Date arrTime;
    public Date depTIme;
    public long timeLeft; //needs to be constantly updated in activity
    public String type;
    public boolean delay;

    //Aircraft details
    public String regn;
    public int age;
    public int ATA;

    //Defects details
    public List defects = new ArrayList<Defects>();
    public int numberOfDefects;
    public int numberOfTechnicians;
    public boolean assigned;
    public boolean inProgress;
    public boolean completed;

    Plane(String regn, String bay, Date arrTime, Date depTIme, int age, int ATA)
    {
        this.regn = regn;
        this.bay = bay;
        this.arrTime = arrTime;
        this.depTIme = depTIme;
        this.timeLeft = depTIme.getTime() - currentTimeMillis();
        this.age = age;
        this.ATA = ATA;

        for (Object child : Database.defects)
        {
            Defects newChild = (Defects) child;
            if (newChild.regn.equals(regn))
                this.defects.add(newChild);
        }
        this.numberOfDefects = this.defects.size();
    }

    public Plane(PlaneData planeData){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        this.regn = planeData.getId();
        this.bay = planeData.getBay();
        try {
            this.arrTime = df.parse(planeData.getArrDate() + ' ' + planeData.getArrTime());
            this.depTIme = df.parse(planeData.getDepDate() + ' ' + planeData.getDepTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.timeLeft = depTIme.getTime() - currentTimeMillis();
    }

    public void autoAssignTechnicians()
    {
        if (numberOfTechnicians < numberOfDefects)
        {
            List technicians = new ArrayList<Technicians>(); //Creates a list of technicians for this plane
            for(Object child : Database.technicians)
            {
                Technicians newChild = (Technicians) child;
                if (technicians.size() < numberOfTechnicians)
                    technicians.add(newChild);
                else
                {
                    for (Object grandchild : technicians) //compares size of tasks of the current technicians list with the newChild
                    {
                        Technicians newGrandchild = (Technicians) grandchild;
                        if(newChild.allTasks.size() < newGrandchild.allTasks.size())
                        {
                            technicians.remove(newGrandchild);
                            technicians.add(newChild);
                        }
                    }
                }
            }

            for (Object child : technicians)
            {
                ((Technicians) child).addPlane(this);
            }
        }
    }
}
