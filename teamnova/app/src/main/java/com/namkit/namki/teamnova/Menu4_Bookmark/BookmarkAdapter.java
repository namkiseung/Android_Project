package com.namkit.namki.teamnova.Menu4_Bookmark;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.namkit.namki.teamnova.R;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-14.
 */

public class BookmarkAdapter extends BaseAdapter {
    private ArrayList<Bookmark> bookmarks;
    Boolean CHECK_STATUS = false; //데이터 삭제시 참거짓 값
    private Context mContext;
    private ArrayList<Bookmark> arrlist2 = new ArrayList<>();
    private Boolean INIT = false;
    CheckBox checkBox2;
    public BookmarkAdapter(Context context, ArrayList<Bookmark> bookmarks) {
        this.mContext = context;
        this.bookmarks = bookmarks;
    }
/*
    public boolean hasDuplicate(Bookmark bookmark){// ArrayList에 내장된 메서드도 가능하지만, '중복' 된다고 비교하는것은 우리가 직접 만든 객체
        //Bookmark 객체에서 equals()로 재정의 해주어야 정상 동작한다

        boolean isDuplicate = false;
        for(int i=0; i<bookmarks.size(); i++){
            Bookmark current = bookmarks.get(i);
            if(current.getUrl() == bookmark.getUrl()){
                isDuplicate = true;
            }
        }
        return bookmarks.contains(bookmark);
    }*/

    @Override
    public int getCount() {
        return bookmarks.size();
    }

    @Override
    public Object getItem(int i) {
        return bookmarks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.bmitem, null);
        }
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBox_newsitem);
        final int checkposition = i;

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bookmarks.get(checkposition).setChecked(isChecked);
            }
        });

        if (CHECK_STATUS) checkBox2.setVisibility(View.VISIBLE);
        else checkBox2.setVisibility(View.GONE);
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title); // 각 리스트마다 즐겨찾기의 제목을 표시 해주기 위해 해당id가진 텍스트뷰 불러옴
        TextView tv_url = (TextView)view.findViewById(R.id.tv_url);

        tv_url.setText(bookmarks.get(i).getUrl());//해당 즐겨찾의 제목을 반환하는것
        tv_title.setText(bookmarks.get(i).getTitle());

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(bookmarks.get(checkposition).getUrl()));
                mContext.startActivity(web);
                return false;
            }
        });
        Animation animation3 = AnimationUtils.loadAnimation(mContext, R.anim.slide_left); //에니메이션 적용
        view.startAnimation(animation3); //애니메이션 적용
        return view;
    }
    public void update() {
        this.notifyDataSetChanged();
    }
    public void setCheckBox(Boolean b) {
        this.CHECK_STATUS = b;
        this.notifyDataSetChanged();
    }
    public void deleteList() {
        ArrayList<Bookmark> temp = new ArrayList<>();
        for (int i = 0; i < bookmarks.size(); i++) {
            if (bookmarks.get(i).getChecked()) {
                temp.add(bookmarks.get(i));
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            bookmarks.remove(temp.get(i));
            arrlist2.remove(temp.get(i));
        }
        this.notifyDataSetChanged();
    }
    public void changeInit(){
        INIT = false;
        arrlist2.clear();
    }
}
