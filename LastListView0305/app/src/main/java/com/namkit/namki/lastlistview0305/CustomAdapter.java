package com.namkit.namki.lastlistview0305;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-06.
 */

public class CustomAdapter extends BaseAdapter{
    Context context;
    String[] names = {"레벨", "엑소", "방구", "뿜", "자이언티", "장예원", "카타무라타"};

    String[] price = {"1000", "2000", "3000", "4000", "5000", "7000", "3000"};


    public CustomAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int pos) {
        return names[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertVIew, ViewGroup viewGroup) {
        Log.d("getVIew","getVIew호출 그리고 convertVIew는?"+convertVIew);
        if(convertVIew == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertVIew = inflater.inflate(R.layout.mobiles, null);

        }
        //get view
        TextView nametxt = (TextView)convertVIew.findViewById(R.id.name);
        TextView pricetxt = (TextView)convertVIew.findViewById(R.id.price);

        //set view
        nametxt.setText(names[pos]);
        pricetxt.setText(price[pos]);
      //  Log.d("getVIew","getVIew호출 그리고 setText(price[pos]는?"+price[pos]);
        return convertVIew;
    }
}
