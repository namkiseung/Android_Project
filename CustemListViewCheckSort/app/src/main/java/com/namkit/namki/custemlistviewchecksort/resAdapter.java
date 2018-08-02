package com.namkit.namki.custemlistviewchecksort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by namki on 2018-03-24.
 */

public class resAdapter extends BaseAdapter {
    private ArrayList<restaurant> datalist ;
    private ArrayList<restaurant> arrlist = new ArrayList<>() ;
    private Context context;

    final static int NAME_ASC = 0;
    final static int TYPE_ASC = 1;

    Boolean CHECK_STATUS = false;

    private Boolean INIT = false;

    Comparator<restaurant> nameAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant restaurant, restaurant t1) {
            return restaurant.getName().compareTo(t1.getName());
        }
    };

    Comparator<restaurant> typeAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant restaurant, restaurant t1) {
            return (restaurant.getType() + "").compareTo(t1.getType() + "");
        }
    };

    public resAdapter(ArrayList<restaurant> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    public void setCheckBox(Boolean b) {
        this.CHECK_STATUS = b;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        TextView txt1 = (TextView) view.findViewById(R.id.textName);
        TextView txt2 = (TextView) view.findViewById(R.id.textTel);
        ImageView img = (ImageView) view.findViewById(R.id.imageview);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        final int position = i;

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                datalist.get(position).setChecked(b);
            }
        });

        if (CHECK_STATUS) checkBox.setVisibility(View.VISIBLE);
        else checkBox.setVisibility(View.GONE);

        if (datalist.get(i).getType() == 1) img.setImageResource(R.mipmap.ic_launcher);
        else if (datalist.get(i).getType() == 2) img.setImageResource(R.mipmap.ic_launcher);
        else img.setImageResource(R.mipmap.ic_launcher);

        txt1.setText(datalist.get(i).getName());
        txt2.setText(datalist.get(i).getTell());


        return view;
    }

    public void setSort(int sortType) {
        if (sortType == NAME_ASC) {
            Collections.sort(datalist, nameAsc);
        } else {
            Collections.sort(datalist, typeAsc);
        }
        this.notifyDataSetChanged();
    }

    public void deleteList() {
        ArrayList<restaurant> temp = new ArrayList<>();
        for (int i = 0; i < datalist.size(); i++) {
            if (datalist.get(i).getChecked()) {
                temp.add(datalist.get(i));
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            datalist.remove(temp.get(i));
            arrlist.remove(temp.get(i));
        }
        this.notifyDataSetChanged();
    }

    public void changeInit(){
        INIT = false;
        arrlist.clear();
    }

    public void searchList(String s) {
        if(!INIT){
            arrlist.addAll(datalist);
            INIT = true;
        }

        datalist.clear();
        if(s.length() == 0){
            datalist.addAll(arrlist);
        }else{
            for(int i = 0 ; i < arrlist.size(); i++){
                if(arrlist.get(i).getName().contains(s)){
                    datalist.add(arrlist.get(i));
                }
            }
        }
        this.notifyDataSetChanged();
    }
}