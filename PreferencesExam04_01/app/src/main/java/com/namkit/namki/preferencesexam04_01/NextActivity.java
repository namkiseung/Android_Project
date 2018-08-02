package com.namkit.namki.preferencesexam04_01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NextActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String KEY_MY_PREFERENCE = "my_preference";
    TextView textView;
    String recive;
    ArrayList<String> arrayList;
    static String publingText;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        loadData();

        try {
            Intent intent = getIntent();
            recive = intent.getExtras().getString("입력내용");
            arrayList.add(arrayList.get(i));
        } catch (NullPointerException e) {

        }
//이전으로 버튼과 임시저장하기 버튼
        textView = (TextView) findViewById(R.id.nextText1);
        this.publingText = arrayList.get(i);
        textView.setText(arrayList.get(i));

        Button btn = (Button) findViewById(R.id.previousButton);
        Button bt2 = (Button) findViewById(R.id.saveButton);
        btn.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                /*SharedPreferences pref = getSharedPreferences(KEY_MY_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                Set<String> myString = pref.getStringSet(("p", new HashSet<String>()));
                editor.commit();*/
                saveData();
            break;
            case R.id.previousButton:
                Intent intent = new Intent(NextActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        arrayList = gson.fromJson(json, type);

        if(arrayList == null){
            arrayList = new ArrayList<String>();
        }
    }
    protected void onResume() {
        super.onResume();

    }
   /* protected void onStop() {
        super.onStop();
        // 데이타를저장합니다.
        SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_MY_PREFERENCE, String.valueOf(textView));
        editor.commit();
        //Toast.makeText(NextActivity.this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();
    }*/
}
