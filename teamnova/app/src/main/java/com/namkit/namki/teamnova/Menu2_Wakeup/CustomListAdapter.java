package com.namkit.namki.teamnova.Menu2_Wakeup;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.namkit.namki.teamnova.R;

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
    private Boolean INIT = false;
    int upposition=0;
    CheckBox checkBox;
    ImageView imageView;
    TextView txtName;
    TextView txtPrice;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WakeupProduct> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.products = objects;
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
    public void changeInit(){
        INIT = false;
        arrlist.clear();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.wakeup_custom_list_layout, null, true);
        }
        WakeupProduct product = getItem(position); //현재 Product의 position 값을 정한다.

        //커스텀 뷰를 가져온다
        checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        imageView = (ImageView)convertView.findViewById(R.id.imageViewProduct);
        txtName = (TextView)convertView.findViewById(R.id.txtName);
        txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        //포지션 값 가지기

        upposition = position;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                products.get(position).setChecked(b);
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
        txtPrice.setText(product.getTime());
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in); //에니메이션 적용
        convertView.startAnimation(animation); //애니메이션 적용
        return convertView;
    }

}
