package com.namkit.namki.imagelistviewpicasso;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by namki on 2018-03-22.
 */

public class CustomListAdapter extends ArrayAdapter<Product>{

    ArrayList<Product> products;
    Context context;
    int resource;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }
        Product product = getItem(position); //현재 Product의 position 값을 정한다.
        //커스텀 뷰를 가져온다
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewProduct);
        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        //피카소 라이브러리 사용
        Picasso.with(context).load(product.getImage()).into(imageView);

        //가져온 것을 어뎁터에 set해준다
        txtName.setText(product.getName());
        txtPrice.setText(product.getPrice());
        return convertView;
    }
}
