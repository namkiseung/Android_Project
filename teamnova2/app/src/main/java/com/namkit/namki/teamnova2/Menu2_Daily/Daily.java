package com.namkit.namki.teamnova2.Menu2_Daily;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.namkit.namki.teamnova2.R;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Daily extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener, Toolbar.OnMenuItemClickListener {

    public static final int REQUEST_CODE_ANOTHER = 1001;
    int id = -1;
    int count=1;//삭제 눌렸는제 체크
    private ListView mListView;
    Animation uptodown, downtoup;
    Daily_Adapter_List adapter;
    List<Daily_MainItem> data = new ArrayList<Daily_MainItem>();
    Button daily_date_del;
    //Sub클래스에서 받아오는 데이터를 담을 변수
    String title, desc, cate;
    EditText tv; // 검색창

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        //검색 창
        tv = (EditText) findViewById(R.id.tv);
        tv.setVisibility(View.GONE);
        ////툴바 붙히기
        Toolbar toolbar2 = (Toolbar)findViewById(R.id.dailytoolBar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 리스너 붙히기
        toolbar2.setOnMenuItemClickListener(this);
        daily_date_del = (Button)findViewById(R.id.btn_daily_del);
        //loadData();
        ///////데이터 세팅(뷰 ID)
        mListView = (ListView) findViewById(R.id.my_listview);                                         /** ListView 타입의 메인.xml에 있는 ListView의 ID를 가져와서 mListView로 선언*/

        mListView.setOnItemClickListener(this); // 한번 클릭 이벤트       /** 리스트의 아이템 클릭시 이벤트시작*/
        mListView.setOnItemLongClickListener(this); //롱클릭 이벤트

        data.add(new Daily_MainItem("각 이유(1)", "글쓰기연습", "3월 24일, 2018년"));
        data.add(new Daily_MainItem("아 이유(2)", "피드백", "3월 24일, 2018년"));
        data.add(new Daily_MainItem("우 이유(3)", "피드백", "3월 24일, 2018년"));
        data.add(new Daily_MainItem("각 이유(4)", "생각정리", "3월 25일, 2018년"));
        data.add(new Daily_MainItem("아 이유(5)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("각 이유(6)", "글쓰기연습", "3월 25일, 2018"));
        data.add(new Daily_MainItem("아 이유(7)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("우 이유(8)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("각 이유(9)", "생각정리", "3월 25일, 2018"));
        data.add(new Daily_MainItem("아 이유(10)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("각 이유(11)", "글쓰기연습", "3월 25일, 2018"));
        data.add(new Daily_MainItem("아 이유(12)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("우 이유(13)", "피드백", "3월 25일, 2018"));
        data.add(new Daily_MainItem("각 이유(14)", "생각정리", "3월 25일, 2018"));
        data.add(new Daily_MainItem("아 이유(15)", "피드백", "3월 25일, 2018"));
        adapter = new Daily_Adapter_List(getApplicationContext(), data); /** 데이터는 어댑터에 들어갔으니 어댑터를 리스트뷰에 장착하자*/
        mListView.setAdapter(adapter);

        //플로팅 버튼 이벤트
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.float_daily_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Daily.this, Sub_daily.class);
                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
            }
        });
        daily_date_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCheckBox(false);
                daily_date_del.setVisibility(View.GONE);
                adapter.deleteList();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent dataintent) {
        super.onActivityResult(requestCode, resultCode, dataintent);
        if (requestCode == REQUEST_CODE_ANOTHER) {
            if (resultCode == RESULT_OK) {
                title = dataintent.getStringExtra("titleed");
                desc = dataintent.getStringExtra("contented");
                cate = dataintent.getStringExtra("categoryed");

                // saveData();
                //시간
                SimpleDateFormat curFormat = new SimpleDateFormat("MMMM"+" dd"+"일,\n"+"yyyy"+"년");
                Date dateObj = new Date(System.currentTimeMillis());
                String thisdate = curFormat.format(dateObj);
                data.add(new Daily_MainItem(title, cate, thisdate));

                adapter = new Daily_Adapter_List(getApplicationContext(), data); /** 데이터는 어댑터에 들어갔으니 어댑터를 리스트뷰에 장착하자*/
                mListView.setAdapter(adapter);

            }else{
              //  Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*private void loaddate(){
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String memoname = preferences.getString("currentdisplayId", "");
        SharedPreferences sharedPreferences = getSharedPreferences(memoname + "daily", MODE_PRIVATE); // memoname이 바뀔때마다 새로운 음악이 저장될 것임. 그리고 어차피 모든 계장이 봐야하니까 songname만 다르게.
        title = sharedPreferences.getString("titleed", "");
        desc = sharedPreferences.getString("contented", "");
        cate = sharedPreferences.getString("categoryed", "");
        System.out.println("loaddata() 안 MainActivity 값 풀어보자"+title +" 그리고 "+ desc +" 그리고 "+ cate);
        data.add(new Daily_MainItem(cate, desc, title));
    }*/
    private void saveData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String memoname = preferences.getString("currentdisplayId", "");
        SharedPreferences sharedPreferences = getSharedPreferences(memoname+"daily", MODE_PRIVATE); // songname이 바뀔때마다 새로운 음악이 저장될 것임. 그리고 어차피 모든 계장이 봐야하니까 songname만 다르게.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Daily_MainItem>>() {
        }.getType();//read
        String json = gson.toJson(data, type); // save
        editor.putString("task list1", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String memoname = preferences.getString("currentdisplayId", "");
        SharedPreferences sharedPreferences = getSharedPreferences(memoname+"daily", MODE_PRIVATE);
        Gson gson = new Gson(); //to Retreive
        String json = sharedPreferences.getString("task list1", ""); //to Retreive
        Type type = new TypeToken<ArrayList<Daily_MainItem>>() {
        }.getType();//read
        data = gson.fromJson(json, type); //to Retreive

        if (data != null) {
            for(int i=0; i<data.size(); i++) {
                adapter = new Daily_Adapter_List(getApplicationContext(), data); /** 데이터는 어댑터에 들어갔으니 어댑터를 리스트뷰에 장착하자*/
                mListView.setAdapter(adapter);
            }
        }else{}
    }

    /**
     * parent는 mListView를 가르킨다 (리스트뷰를 말한다)
     * view는 클릭한 리스트의 한칸을 말한다.
     * positon과 id는 비슷(위치를 알린다)
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        tv.setAnimation(downtoup);
        tv.setVisibility(View.GONE);


        //Toast.makeText(this, "position" + position, Toast.LENGTH_SHORT).show();
        id = position;
    }

    /**
     * 대부분의 boolean 타입은 했는지 안했는지 구별용도
     * return false 설정안한게 된다.
     * return true 여기서 이벤트를 끝낸다
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        data.remove(i);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onClick(View view) {

    }
    public void onPause(){
        super.onPause();
        //loaddate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.daily_item, menu2);
        return true;
    }
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dailyfirst: //키워드순 정렬(X)->키워드 검색
                //adapter.setSort(adapter.NAME_ASC);
                //adapter.notifyDataSetChanged();
                tv.setTextSize(15);
                tv.setHint("키워드를 검색하시오. \n (개발일지, 피드백, 글쓰기, 생각정리)");
                search_key(1);
                //Toast.makeText(this, "키워드검색", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dailysecond://시간순 정렬(X)->제목으로 검색
                search_key(2);
                tv.setTextSize(20);
                tv.setHint("제목을 검색하시오.");
                //adapter.setSort(adapter.TYPE_ASC);
                //adapter.notifyDataSetChanged();
                //Toast.makeText(this, "제목 검색", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dailythird: //삭제
                    daily_date_del.setVisibility(View.VISIBLE);
                    adapter.setCheckBox(true);
                break;

        }
        return false;
    }

    private void search_key(final int wall){
        tv.setVisibility(View.VISIBLE);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        tv.setAnimation(uptodown);
        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String search = editable.toString();
                adapter.searchList(search, wall);
            }
        });
    }


}
