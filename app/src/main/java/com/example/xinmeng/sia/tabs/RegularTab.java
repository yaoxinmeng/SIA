package com.example.xinmeng.sia.tabs;

/**
 * Created by Admin on 26/10/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.example.xinmeng.sia.R;

public class RegularTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.regulartab, container, false);

        return rootView;
    }

}