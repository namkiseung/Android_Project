package com.namkit.namki.examlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namki on 2018-03-01.
 */

public class MyListAdapter extends BaseAdapter{
    TextView nickname_textView;
    TextView title_textView;
    TextView date_textView;
    TextView content_textView;
    ImageView profile_imageView;
    Context context;
    ArrayList<list_item> list_itemsarray;

    public MyListAdapter(Context context, ArrayList<list_item> list_itemsarray) {
        this.context = context;
        this.list_itemsarray = list_itemsarray;
    }


    //리스트뷰에 표시할 아이템의 갯수
    @Override
    public int getCount() { //아이템 개수
        return this.list_itemsarray.size();
    }

    //해당 포지션에 위치하는 아이템을 리턴 받는 애
    @Override
    public Object getItem(int i) { //아이템 가져오는거
        return list_itemsarray.get(i);
    }

    // 해당 포지션에 위치하는 아이템의 ID
    @Override
    public long getItemId(int i) { //
        return i;
    }

    // 각 아이템의 레이아웃을 결정하는 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //뷰 가져오기
        //getView의 int i는 해당 위치의 뷰를 만들어 달라는 것
        //View는 재활용 하려면 해라 라고 넘겨주는 view
        //checkbox.setChecked(false);

        //Toast.makeText(context, "호출 횟수"+i, Toast.LENGTH_SHORT).show();

        if(view == null) {
            //LayoutInflater mLayoutinflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = mLayoutinflater.inflate(R.layout.actionlistitem, null);
            view = LayoutInflater.from(context).inflate(R.layout.item, null);

            nickname_textView = (TextView) view.findViewById(R.id.nickname_textview);
            content_textView = (TextView) view.findViewById(R.id.content_texview);
            date_textView = (TextView) view.findViewById(R.id.date_textview);
            title_textView = (TextView) view.findViewById(R.id.title_textview);
            profile_imageView = (ImageView) view.findViewById(R.id.profile_imageView);
        }
        //뷰 뿌리주기
        nickname_textView.setText(list_itemsarray.get(i).getNickname());
        title_textView.setText(list_itemsarray.get(i).getTitle());
        content_textView.setText(list_itemsarray.get(i).getContent());
        date_textView.setText(list_itemsarray.get(i).getWrite_date().toString());
        profile_imageView.setImageResource(list_itemsarray.get(i).getProfile_image());

        return view;
    }
}
