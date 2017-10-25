package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    public void nextPage() //for next page button
    {
        page++;
        onResume();
    }

    public void prevPage() //for previous page button
    {
        if (page != 1)
            page--;
        onResume();
    }
}
