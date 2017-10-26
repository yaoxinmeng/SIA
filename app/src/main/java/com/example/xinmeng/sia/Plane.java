package com.example.xinmeng.sia;


import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.example.xinmeng.sia.ViewHolders.PlaneData;

import java.io.IOException;
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

    //Defects details
    public ArrayList<Defects> defects;
    public int numberOfDefects;
    public int numberOfTechnicians;
    public boolean assigned;
    public boolean inProgress;
    public boolean completed;

    //For serialization
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException{

    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {

    }

    private void readObjectNoData()
            throws ObjectStreamException {

    }


    public Plane(){}
    public Plane(String regn, String bay, Date arrTime, Date depTIme)
    {
        this.regn = regn;
        this.bay = bay;
        this.arrTime = arrTime;
        this.depTIme = depTIme;
        this.timeLeft = depTIme.getTime() - currentTimeMillis();

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
            List <Technicians> technicians = new ArrayList<Technicians>(); //Creates a list of technicians for this plane
            for(Technicians child : Database.technicians)
            {
                if (technicians.size() < numberOfTechnicians)
                    technicians.add(child);
                else
                {
                    for (Technicians grandchild : technicians) //compares size of tasks of the current technicians list with the newChild
                    {
                        if(child.allTasks.size() < grandchild.allTasks.size())
                        {
                            technicians.remove(grandchild);
                            technicians.add(child);
                        }
                    }
                }
            }

            for (Technicians child : technicians)
            {
                child.addPlane(this);
            }
        }
    }
}
