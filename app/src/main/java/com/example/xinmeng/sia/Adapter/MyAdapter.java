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
import java.util.List;

/**
 * Created by Admin on 25/10/2017.
 */

public class MyAdapter<D> extends ArrayAdapter<DefectsFetcher> {

    private Context mContext;
    int mResource;

    public MyAdapter (Context context, int resource, ArrayList<DefectsFetcher> objects){
        super (context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate((R.layout.tech_tabinterface),parent,false);

        TextView defect_header = (TextView) customView.findViewById(R.id.defect_header);
        TextView defect_content = (TextView) customView.findViewById(R.id.defect_content);
        Button defect_button = (Button) customView.findViewById(R.id.defectButton);

        //defect_header.setText(DefectsFetcher.getId());
        //defect_content.setText(DefectsFetcher.getRegn);
        return customView;

    }


}

