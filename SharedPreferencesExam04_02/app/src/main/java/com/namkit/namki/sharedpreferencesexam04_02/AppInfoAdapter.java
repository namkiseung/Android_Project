package com.namkit.namki.sharedpreferencesexam04_02;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by namki on 2018-03-09.
 */

class AppInfoAdapter extends BaseAdapter{
    //일단 데이터를 받자
    private List<ApplicationInfo> mInfos;

    public AppInfoAdapter(List<ApplicationInfo> data) {
        this.mInfos = data;
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holdolder;

        if(convertView == null){
            holdolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
            holdolder.imageView = convertView.findViewById(R.id.icon_image);
            holdolder.textView = convertView.findViewById(R.id.app_name_text);
            convertView.setTag(holdolder);
        }
        holdolder = (ViewHolder) convertView.getTag();

        ApplicationInfo info = mInfos.get(i);

        Drawable icon = info.loadIcon(parent.getContext().getPackageManager());
        holdolder.imageView.setImageDrawable(icon);

        String name = String.valueOf(info.loadLabel(parent.getContext().getPackageManager()));
        holdolder.textView.setText(name);
        return convertView;
    }
    private static class ViewHolder{//static으로 선언해야 메모리릭의 발생 여지를 아예 없앨 수 있다
        ImageView imageView;
        TextView textView;
    }
}
