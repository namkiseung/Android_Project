package com.namkit.namki.a0507progresswork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-16.
 */

public class MyListAdapter extends BaseAdapter{
    private ArrayList<list_item> list_itemsArrayList;

    public MyListAdapter() {
        list_itemsArrayList = new ArrayList<>();
    }
    public void addItem(list_item list_item){
        list_itemsArrayList.add(list_item);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list_itemsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_itemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final Context context = convertView.getContext();
        final list_item list_item = list_itemsArrayList.get(position);
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent,false);
            viewHolder.named = (TextView)convertView.findViewById(R.id.ddname);
            viewHolder.conditiond = (TextView)convertView.findViewById(R.id.ddcondition);
            viewHolder.dtimed = (TextView)convertView.findViewById(R.id.dddtime);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.named.setText(list_itemsArrayList.get(position).getName());
        viewHolder.conditiond.setText(list_itemsArrayList.get(position).getContent());
        viewHolder.dtimed.setText(list_itemsArrayList.get(position).getDate());
        return convertView;
    }
    public void removeitem(int position){
        list_itemsArrayList.remove(position);
    }
}
class ViewHolder{
    TextView named, conditiond, dtimed;
}