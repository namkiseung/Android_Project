package com.namkit.namki.examlistview;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    List<list_item> list_itemArrayList;
    MyListAdapter myListAdapter;
    FloatingActionButton newtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newtext = (FloatingActionButton)findViewById(R.id.fab);
        newtext.setOnClickListener(this);

        listView = (ListView)findViewById(R.id.list);
        list_itemArrayList = new ArrayList<list_item>();

        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"빨강","제목1",new Date(System.currentTimeMillis()),"내용1"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"팀노바","제목2",new Date(System.currentTimeMillis()),"내용2"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"나나","제목3",new Date(System.currentTimeMillis()),"내용3"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"핸드폰","제목4",new Date(System.currentTimeMillis()),"내용4"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"햇님","제목5",new Date(System.currentTimeMillis()),"내용5"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"달님","제목6",new Date(System.currentTimeMillis()),"내용5"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"마우스님","제목7",new Date(System.currentTimeMillis()),"내용5"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"레노버님","제목8",new Date(System.currentTimeMillis()),"내용5"));
        list_itemArrayList.add(
                new list_item(R.mipmap.ic_launcher,"물티슈님","제목9", new Date(System.currentTimeMillis()),"내용5"));

        myListAdapter = new MyListAdapter(MainActivity.this, (ArrayList<list_item>) list_itemArrayList);
        listView.setAdapter(myListAdapter);
    }
    @Override
    protected void onPause(){
        super.onPause();

    }




    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fab:
                    Intent intent = new Intent(MainActivity.this, New_text.class);
                    startActivity(intent);
                break;
        }

    }
}
