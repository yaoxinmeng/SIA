package com.example.xinmeng.sia.Adapter;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.xinmeng.sia.DefectsFetcher;
import com.example.xinmeng.sia.R;

import java.util.ArrayList;


public class SpareListAdapter extends ArrayAdapter<String>{

    private Context mContext;
    int mResource;


    public SpareListAdapter(@NonNull Context context, @LayoutRes int resource,  ArrayList<String> objects) {
        super(context, resource,objects);

        mContext = context;
        mResource = resource;
    }


    public View getView(int position, View convertView, ViewGroup parent){


        LayoutInflater myInflater = LayoutInflater.from(mContext);
        View customView = myInflater.inflate(mResource,parent,false);

        //TextView spare_header = (TextView) customView.findViewById(R.id.spare_header);
        TextView spare_content = (TextView) customView.findViewById(R.id.spare_content);


        //spare_header.setText(getItem(position).getId());
        spare_content.setText(getItem(position).toString());
        return customView;

    }


}