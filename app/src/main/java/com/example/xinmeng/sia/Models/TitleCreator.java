package com.example.xinmeng.sia.Models;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Admin on 25/10/2017.
 */

public class TitleCreator {
    static TitleCreator _titleCreator;
    List<TitleParent> _titleParents;

    public TitleCreator(Context context){
         TitleParent _titleParents = new TitleParent(String.format("Caller %d",1));
    }

    public static TitleCreator get(Context context){
        if(_titleCreator==null)
            _titleCreator = new TitleCreator(context);
        return _titleCreator;
    }

    public List<TitleParent> getAll() {
        return _titleParents;
    }
}
