package com.example.xinmeng.sia.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

/**
 * Created by Admin on 25/10/2017.
 */

public class TitleParent implements ParentObject {

    private List<Object> mChildrenList;
    private UUID _id;
    private String title;

    public TitleParent(String title) {
        this.title = title;
        _id = UUID.randomUUID();
    }

    public UUID get_id(){
        return _id;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public String getTitle() {
        return title;
    }
}

