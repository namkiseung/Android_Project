package com.namkit.namki.bookmarkapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.namkit.namki.bookmarkapplication.models.Bookmark;

import java.util.ArrayList;

/**
 * Created by namki on 2018-03-10.
 */

public class BookmarkAdapter extends BaseAdapter{
    private ArrayList<Bookmark> bookmarks;

    public BookmarkAdapter() {
        bookmarks = new ArrayList<>();
    }

    public boolean hasDuplicate(Bookmark bookmark){// ArrayList에 내장된 메서드도 가능하지만, '중복' 된다고 비교하는것은 우리가 직접 만든 객체
        //Bookmark 객체에서 equals()로 재정의 해주어야 정상 동작한다

        boolean isDuplicate = false;
        for(int i=0; i<bookmarks.size(); i++){
            Bookmark current = bookmarks.get(i);
            if(current.getUrl() == bookmark.getUrl()){
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
    public void addBookmark(Bookmark bookmark){//bookmark 객체를 받아서 bookmarks에 그대로 추가해주는 일. 북마크 추가버튼 누르면 addBookmark()가 실행
        bookmarks.add(bookmark);
        notifyDataSetChanged(); // 자료가 바뀌면 바뀌는 대로 리스트를 업데이트 해준다.
    }
    public void delBookmark(Bookmark bookmark, int pos){//bookmark 객체를 받아서 bookmarks에 그대로 추가해주는 일. 북마크 추가버튼 누르면 addBookmark()가 실행
        bookmarks.remove(bookmarks.get(pos));
        notifyDataSetChanged(); // 자료가 바뀌면 바뀌는 대로 리스트를 업데이트 해준다.
    }
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
        //이메소드에서 우리가 만든 item_bookmark 레이아웃을 연결
        final String URL = "url";
        final Context context = viewGroup.getContext();
        final Bookmark bookmark = bookmarks.get(i); //bookmark는 해당 position에 있는 즐겨찾기 하나
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_bookmark, viewGroup, false);
        }

        TextView tv_title = (TextView)view.findViewById(R.id.tv_title); // 각 리스트마다 즐겨찾기의 제목을 표시 해주기 위해 해당id가진 텍스트뷰 불러옴
        tv_title.setText(bookmark.getTitle());//해당 즐겨찾의 제목을 반환하는것
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrowseActivity.class);
                intent.putExtra(URL, bookmark.getUrl());//(키, 값) 순
                context.startActivity(intent);//어댑터를 사용하는 액티비티에서 Intent가 동작하게 된다
            }
        });
        return view;
    }
}
