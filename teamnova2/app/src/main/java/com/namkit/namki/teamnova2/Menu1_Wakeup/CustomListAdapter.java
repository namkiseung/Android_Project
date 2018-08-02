package com.namkit.namki.teamnova2.Menu1_Wakeup;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.namkit.namki.teamnova2.R;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-22.
 */

public class CustomListAdapter extends ArrayAdapter<WakeupProduct>{
    private ArrayList<WakeupProduct> arrlist = new ArrayList<>();
    ArrayList<WakeupProduct> products;
    Context context;
    int resource;
    Boolean CHECK_STATUS = false;
    int upposition=0;
    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WakeupProduct> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }
    public void setCheckBox(Boolean b) {
        this.CHECK_STATUS = b;
        this.notifyDataSetChanged();
    }
    public void deleteList() {
        ArrayList<WakeupProduct> temp = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getChecked()) {
                temp.add(products.get(i));
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            products.remove(temp.get(i));
            arrlist.remove(temp.get(i));
        }
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.wakeup_custom_list_layout, null, true);
        }
        WakeupProduct product = getItem(position); //현재 Product의 position 값을 정한다.

        //커스텀 뷰를 가져온다
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageViewProduct);
        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        //포지션 값 가지기
        final int i = position;
        upposition = position;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                products.get(i).setChecked(b);
            }
        });

        if (CHECK_STATUS) checkBox.setVisibility(View.VISIBLE);
        else checkBox.setVisibility(View.INVISIBLE);

        //피카소 라이브러리 사용
        //Picasso.with(context).load(product.getImage()).into(imageView);

        Glide.with(context).load(product.getImage()).error(R.drawable.animation_background).into(imageView);
        //Glide.with(context).load(product.getImage()).error(R.drawable.animation_background).into(imageView);

        //가져온 것을 어뎁터에 set해준다
        txtName.setText(product.getName());
        txtPrice.setText(product.getPrice());
        return convertView;
    }
    public void singledeleteList(int i) {
        products.remove(i);
        notifyDataSetChanged();
    }
}
