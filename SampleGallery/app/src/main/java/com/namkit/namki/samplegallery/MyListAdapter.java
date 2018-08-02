package com.namkit.namki.samplegallery;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-16.
 */

public class MyListAdapter extends BaseAdapter{
    Context context;
    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewholder;
    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list_itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            viewholder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewholder.nickname_textView = (TextView)view.findViewById(R.id.nickname_textview);
            viewholder.content_textView = (TextView)view.findViewById(R.id.content_texview);
            viewholder.date_textView = (TextView)view.findViewById(R.id.date_textview);
            viewholder.title_textView  =(TextView)view.findViewById(R.id.title_textview);
            viewholder.profile_imageView = (ImageView)view.findViewById(R.id.profile_imageView);
            view.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)view.getTag();
        }
        viewholder.nickname_textView.setText(list_itemArrayList.get(i).getNickname());
        viewholder.title_textView.setText(list_itemArrayList.get(i).getTitle());
        viewholder.content_textView.setText(list_itemArrayList.get(i).getContent());
        viewholder.date_textView.setText(list_itemArrayList.get(i).getWrite_date().toString());
        Glide.with(context).load(list_itemArrayList.get(i).getProfile_image()).into(viewholder.profile_imageView); //Glide.with(Context).load(“파일경로”).into(이미지뷰); 만 적으면 간단하게  동작
        return view;
    }
}
class ViewHolder{
    TextView nickname_textView;
    TextView content_textView;
    TextView date_textView;
    TextView title_textView;
    ImageView profile_imageView;
}