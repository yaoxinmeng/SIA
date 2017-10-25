package com.example.xinmeng.sia;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.xinmeng.sia.Adapter.MyAdapter;
import com.example.xinmeng.sia.Models.TitleChild;
import com.example.xinmeng.sia.Models.TitleCreator;
import com.example.xinmeng.sia.Models.TitleParent;

import java.util.ArrayList;
import java.util.List;

public class TechnicianTaskDetail extends AppCompatActivity {

    RecyclerView recyclerView;
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter) recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_task_detail);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this,initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);

    }*/

    private void update()
    {
        while (true)
        {
            SystemClock.sleep(10000);
            //whatever that needs to be updated
        }
    }


/*
    private List<ParentObject> initData(){
        TitleCreator titleCreator = TitleCreator.get(this);
        List<TitleParent> titles = titleCreator.getAll();
        List <ParentObject> parentObjects = new ArrayList<>();
        for (TitleParent title:titles)
        {
            List<Object> childList = new ArrayList<>() ;
            childList.add(new TitleChild("Add to contacts"));
            title.setChildObjectList(childList);
            parentObjects.add(title);
        }
        return parentObjects;
    }
*/

}
