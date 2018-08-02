package com.namkit.namki.teamnova.Menu4_Bookmark;


import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.namkit.namki.teamnova.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity implements View.OnClickListener, BookDialog.BookmarkDialog {

    private ListView lv_bookmark; //리스트뷰
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton Bweb, Badd, Bdel;
    String Rtitle, Rurl;
    Button del;
    ArrayList<Bookmark> bookarray = new ArrayList<Bookmark>();
    BookmarkAdapter bookmarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        loadData();
        init();
        bookmarkAdapter = new BookmarkAdapter(this, bookarray);
        lv_bookmark.setAdapter(bookmarkAdapter);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkAdapter.setCheckBox(false);
                del.setVisibility(View.GONE);
                bookmarkAdapter.deleteList();
               /* if (del.getText().toString().equals("선택삭제")) {
                    del.setText("삭제");
                    bookmarkAdapter.setCheckBox(true);
                } else {
                    del.setText("선택삭제");
                    bookmarkAdapter.deleteList();
                    bookmarkAdapter.setCheckBox(false);
                }*/
                saveData();
            }
        });
    }
    public void init() {
        lv_bookmark = (ListView) findViewById(R.id.lv_bookmark);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.floatingActionMenu);
        del = (Button)findViewById(R.id.news_basic_btn_del);
        Bweb = (FloatingActionButton) findViewById(R.id.book_web);
        Badd = (FloatingActionButton) findViewById(R.id.float_book_add);
        Bdel = (FloatingActionButton) findViewById(R.id.book_btn_del);
        Badd.setOnClickListener(this);
        Bdel.setOnClickListener(this);
        Bweb.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.float_book_add:
                BookDialog examDialog = new BookDialog();
                examDialog.show(getSupportFragmentManager(), "BookUrl");
                break;
            case R.id.book_web:
                Toast.makeText(this, "해당 아이템을 길게 누르면, 웹페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.book_btn_del:
                Animation uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
                del.setAnimation(uptodown);
                del.setVisibility(View.VISIBLE);
                bookmarkAdapter.setCheckBox(true);
                break;
        }
    }
    public void applyTexts(String mtitle, String murl) {
        Rurl = murl;
        Rtitle = mtitle;
        if (Rtitle.equals("")) {
            Rtitle = "제목없음";
        } else if (Rtitle.equals("")) {
            Rtitle = "-";
        }
       /* if (bookmarkAdapter.hasDuplicate(bookmark)) {     //중복되었을때
            Toast.makeText(getApplicationContext(), "이미 등록된 주소입니다.", Toast.LENGTH_SHORT).show();
        } else {*/
        bookarray.add(new Bookmark(Rtitle, Rurl));
        bookmarkAdapter.notifyDataSetChanged();
        saveData();
        Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private void saveData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String brower = preferences.getString("currentdisplayId", "");
        SharedPreferences sharedPreferences = getSharedPreferences(brower, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Bookmark>>() {
        }.getType();//read
        String jsong = gson.toJson(bookarray, type); // save
        editor.putString("task list4", jsong);
        editor.apply();
        //bookmarkAdapter.update();
    }

    private void loadData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String brower = preferences.getString("currentdisplayId", "");
        SharedPreferences sharedPreferences = getSharedPreferences(brower, MODE_PRIVATE);
        Gson gson = new Gson();
        String jsong = sharedPreferences.getString("task list4", "");
        Type type = new TypeToken<ArrayList<Bookmark>>() {
        }.getType();//read
        bookarray = gson.fromJson(jsong, type);

        if (bookarray == null) {
            bookarray = new ArrayList<>();
        }
    }

    public void onStart() {
        super.onStart();
       // loadData();
    }
}
