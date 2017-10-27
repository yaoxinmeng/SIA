package com.example.xinmeng.sia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

public class TechnicianTasksMain extends AppCompatActivity {
    private Technicians technician;
    private String techID;
    private Plane firstPlane;
    private ArrayList<Defects> tasks;
    private ArrayList<Defects> tasksDisplayed;

    private final int entryNumber = 4; //number of entries displayed per page
    private int page = 1; //page number
    private int maxPage; //max page possible

    TextView arrTime;
    TextView depTime;
    TextView bay;
    TextView type;
    TextView regn;
    ImageView delayed;
    ImageView onTime;
    ImageView arrived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_tasks_main);

        Bundle extras = getIntent().getExtras();
        techID = extras.getString("TECH_ID");

        for (Technicians child : Database.technicians)
        {
            if(techID.equals(child.ID))
            {
                technician = child;
                break;
            }
        }
        firstPlane = technician.currentPlane;
        tasks = sortTasks(firstPlane.defects);
        maxPage = (int) (tasks.size() / entryNumber) + 1;

        regn = (TextView) findViewById(R.id.flight_regn);
        bay = (TextView) findViewById(R.id.flight_bay);
        arrTime = (TextView) findViewById(R.id.flight_time_arrival);
        depTime = (TextView) findViewById(R.id.flight_time_departure);
        type = (TextView) findViewById(R.id.flight_actype);
        delayed =(ImageView) findViewById(R.id.status_delayed);
        onTime =(ImageView) findViewById(R.id.status_ontime);
        arrived = (ImageView) findViewById(R.id.status_arrived);

        setupPage();
    }

    private void setupPage()
    {
        firstPlane = technician.currentPlane;
        tasks = sortTasks(firstPlane.defects);
        maxPage = (int) (tasks.size() / entryNumber) + 1;
        setTexts();
        setTasksDisplayed();
    }

    private void setTexts()
    {
        regn.setText(firstPlane.regn);
        bay.setText(firstPlane.bay);
        type.setText(firstPlane.type);
        DateFormat tf = new SimpleDateFormat("HH:mm");

//        long arrtime = firstPlane.arrTime.getTime();
//        long deptime = firstPlane.depTIme.getTime();
        arrTime.setText(tf.format(firstPlane.arrTime));
        depTime.setText(tf.format(firstPlane.depTIme));

        if (currentTimeMillis() < firstPlane.arrTime.getTime()){
            onTime.setVisibility(View.INVISIBLE);
            delayed.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.VISIBLE);}

        else if (firstPlane.delay){
            onTime.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.INVISIBLE);
            delayed.setVisibility(View.VISIBLE);}
        else {
            delayed.setVisibility(View.INVISIBLE);
            arrived.setVisibility(View.INVISIBLE);
            onTime.setVisibility(View.VISIBLE);
        }
    }

    private String getTime(long time)
    {
        float arrtime = (float) time;
        int hour = (int) arrtime;
        int minute = (int) (100 * (arrtime - ((float) hour)));
        if (hour < 10)
        {
            if (minute < 10)
                return "0" + String.valueOf(hour) + ":0" + String.valueOf(minute);
            else
                return "0" + String.valueOf(hour) + ":" + String.valueOf(minute);
        }
        else
        {
            if (minute < 10)
                return String.valueOf(hour) + ":0" + String.valueOf(minute);
            else
                return String.valueOf(hour) + ":" + String.valueOf(minute);
        }
    }
    private void back()
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianMain.class);
        startActivity(intent);
    }
    private ArrayList<Defects> sortTasks(ArrayList<Defects> allTasks)
    {
        ArrayList<Defects> tempList = new ArrayList<>();
        if (allTasks == null)
        {
            return null;
        }
        else
        {
            for (Defects task : allTasks)
            {
                if (tempList.size() == 0)
                    tempList.add(task);
                else
                {
                    for (int n = 0; n < tempList.size(); n++)
                    {
                        if (task.priority < tempList.get(n).priority)
                        {
                            tempList.add(n, task);
                            break;
                        }
                    }
                }
            }
        }
        return tempList;
    }

    private ArrayList<Defects> tasksForDisplay()
    {
        ArrayList<Defects> tempList = new ArrayList<>();
        int n = (page - 1) * entryNumber + 1;
        if (tasks.size() < n)
            return null;

        for (int x = n - 1; x < n + entryNumber - 1; x++)
        {
            try {
                tempList.add(tasks.get(x));
            }
            catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return tempList;
    }

    private void setTasksDisplayed()
    {
        ArrayList<Defects> tempList = tasksForDisplay();
        for (int n = 0; n < ((tempList.size()<entryNumber) ? tempList.size() : entryNumber); n++)
        {
            if (tempList.get(n).resolved)
            {
                //assign resolved image
            }
            else
            {
                //assign unresolved image
            }
        }
    }

    //Buttons
    public void refresh(View view)
    {
        setTexts();
    }
    public void backback (View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianMain.class);
        intent.putExtra("TECH_ID", technician.ID);
        startActivity(intent);
    }

    public void equipment (View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianEquipment.class);
        intent.putExtra("TECH_ID", technician.ID);
        startActivity(intent);
    }

    public void nextPage(View view)
    {
        if (page < maxPage)
            page++;
        setTasksDisplayed();
    }

    public void prevPage(View view)
    {
        if (page != 1)
            page--;
        setTasksDisplayed();
    }

    public void task1(View view)
    {
        if (tasksForDisplay() != null)
        {
            if (tasksForDisplay().get(0) != null)
            {
                Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
                intent.putExtra("TASK_ID", tasksForDisplay().get(0).number);
                intent.putExtra("PLANE_ID", firstPlane.regn);
                startActivity(intent);
            }
        }
    }

    public void task2(View view)
    {
        if (tasksForDisplay() != null)
        {
            if (tasksForDisplay().get(1) != null)
            {
                Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
                intent.putExtra("TASK_ID", tasksForDisplay().get(1).number);
                intent.putExtra("PLANE_ID", firstPlane.regn);
                startActivity(intent);
            }
        }
    }

    public void task3(View view)
    {
        if (tasksForDisplay() != null)
        {
            if (tasksForDisplay().get(2) != null)
            {
                Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
                intent.putExtra("TASK_ID", tasksForDisplay().get(2).number);
                intent.putExtra("PLANE_ID", firstPlane.regn);
                startActivity(intent);
            }
        }
    }

    public void task4(View view)
    {
        if (tasksForDisplay() != null)
        {
            if (tasksForDisplay().get(3) != null)
            {
                Intent intent = new Intent(TechnicianTasksMain.this, TechnicianTaskDetail.class);
                intent.putExtra("TASK_ID", tasksForDisplay().get(3).number);
                intent.putExtra("PLANE_ID", firstPlane.regn);
                startActivity(intent);
            }
        }
    }
    public void back (View view)
    {
        Intent intent = new Intent(TechnicianTasksMain.this, TechnicianMain.class);
        intent.putExtra("TECH_ID", technician.ID);
        startActivity(intent);
    }
}
