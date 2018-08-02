package com.namkit.namki.listviewpasstext;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by namki on 2018-03-07.
 */

public class MyAdapter extends BaseAdapter {
    LayoutInflater inflate;
    Context context;
    ArrayList<Product> arrayList = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() { //화면상에 뿌려질 아이템 갯수
        return arrayList.size();
    }
    @Override
    public Product getItem(int position) { //Object를 반환하는 getItem
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position) //해당 아이템의 Index 값이다
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null) {//해당 아이템이 null일 경우에 뷰를 반환하는 convertView로 아이템.xml을 사용가능한 뷰로 만들어준다
       // }//리소스 재활용 차원에서 화면에 보여질것을 만든다. 미리 만들어 놓을 필요가없다
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflate.inflate(R.layout.item, null);

        TextView text = (TextView)convertView.findViewById(R.id.title);
        Product product = arrayList.get(position);
        text.setText(product.getText());
        return convertView;
    }
}
