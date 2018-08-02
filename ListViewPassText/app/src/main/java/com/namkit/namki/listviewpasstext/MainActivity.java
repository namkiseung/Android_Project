package com.namkit.namki.listviewpasstext;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    ArrayList<Product> arrayList;
    EditText editText;
    Button button;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<Product>();
        button = (Button)findViewById(R.id.btn_add);
        editText = (EditText)findViewById(R.id.edit_text);

        adapter = new MyAdapter(this, arrayList);

        listView = (ListView)findViewById(R.id.list_view);  //xml에 있는 리스트뷰를 객체로 만들었다. (리스트뷰 객체에는 xml파일이 가지고 있는 리스트뷰의 id가 담겨있다)

         arrayList.add(new Product("공지사항"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(new Product(editText.getText().toString()));
            }
        });
    listView.setAdapter(adapter);   //listView 객체에 어댑터 장착.  어댑터는 item.xml을 반복해서 리스트뷰 각각의 아이템에 뿌려준다.
    }
}