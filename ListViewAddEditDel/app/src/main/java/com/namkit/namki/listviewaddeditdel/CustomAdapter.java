package com.namkit.namki.listviewaddeditdel;



import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-06.
 */

public class CustomAdapter extends ArrayAdapter {
    Activity activity;
    int layout;
    ArrayList<SinhVien> arrSinhVien;

    public CustomAdapter(@NonNull Activity activity, @LayoutRes int layout, ArrayList<SinhVien> arrSinhVien) {
        super(activity, layout, arrSinhVien);
        this.activity = activity;
        this.layout = layout;
        this.arrSinhVien = arrSinhVien;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout, null);

        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);
        TextView ten = (TextView)convertView.findViewById(R.id.text_ten);
        TextView sdt = (TextView)convertView.findViewById(R.id.text_sdt);

        avatar.setImageResource(arrSinhVien.get(position).getAvatar());
        ten.setText(arrSinhVien.get(position).getTenSinhVien());
        sdt.setText(arrSinhVien.get(position).getSdtSinhVien());

        return convertView;
    }
}