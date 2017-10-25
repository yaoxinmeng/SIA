package com.example.xinmeng.sia.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.xinmeng.sia.R;

/**
 * Created by Admin on 25/10/2017.
 */

public class TitleChildViewHolder extends ChildViewHolder {
    public TextView option1;

    public TitleChildViewHolder(View itemView) {
        super(itemView);
        option1 = (TextView) itemView.findViewById(R.id.option1);
    }
}
