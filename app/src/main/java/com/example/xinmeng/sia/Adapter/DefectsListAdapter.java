package com.example.xinmeng.sia.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xinmeng.sia.DefectsFetcher;
import com.example.xinmeng.sia.R;

import java.util.ArrayList;

/**
 * Created by Admin on 27/10/2017.
 */

public class DefectsListAdapter extends ArrayAdapter<DefectsFetcher> {

    private Context mContext;
    int mResource;


    public DefectsListAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<DefectsFetcher> objects) {
        super(context, resource,objects);

        mContext = context;
        mResource = resource;
    }


    public View getView(int position, View convertView, ViewGroup parent){


        LayoutInflater myInflater = LayoutInflater.from(mContext);
        View customView = myInflater.inflate(mResource,parent,false);

        TextView defect_header = (TextView) customView.findViewById(R.id.defect_header);
        TextView defect_content = (TextView) customView.findViewById(R.id.defect_content);


        defect_header.setText(getItem(position).getId());
        defect_content.setText(getItem(position).getDefects());
        return customView;

    }

}
