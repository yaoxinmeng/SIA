package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Queue;

public class SupervisorMain extends AppCompatActivity {
    private final int entryNumber = 5; //number of entries displayed per page
    private int page = 1; //page number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_main);
        Queue planeDisplay = planesForDisplay(page);

        update();
    }

    protected void onResume(Bundle savedInstanceState)
    {
        Queue planeDisplay = planesForDisplay(page);
    }

    private Queue planesForDisplay(int pageNumber)
    {
        Queue planesDisplayed = null;
        for (int n = (pageNumber - 1) * entryNumber; n < pageNumber * entryNumber; n++)
        {
            planesDisplayed.add(Database.planes.get(n));
        }
        return planesDisplayed;
    }

    private void update()
    {
        while (true)
        {
            SystemClock.sleep(10000);
            //whatever that needs to be updated
        }
    }

    //Button functions
    public void nextPage(View view) //for next page button
    {
        page++;
        onResume();
    }

    public void prevPage(View view) //for previous page button
    {
        if (page != 1)
            page--;
        onResume();
    }

    public void entry1(View view)
    {
        Plane plane = (Plane) Database.planes.get(page - 1);
    }

    public void entry2(View view)
    {
        Plane plane = (Plane) Database.planes.get(2 * page - 1);
    }

    public void entry3(View view)
    {
        Plane plane = (Plane) Database.planes.get(3 * page - 1);
    }

    public void entry4(View view)
    {
        Plane plane = (Plane) Database.planes.get(4 * page - 1);
    }

    public void entry5(View view)
    {
        Plane plane = (Plane) Database.planes.get(5 * page - 1);
    }
}
