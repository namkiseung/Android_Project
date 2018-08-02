package com.namkit.namki.preferencesexam04_01;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edit;
    String text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = (Button) findViewById(R.id.nextButton);
        Button btn = (Button)findViewById(R.id.aaaaa);
        bt.setOnClickListener(this);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                edit = (EditText) findViewById(R.id.text);
                String text = edit.getText().toString();
                intent.putExtra("입력내용", text);
                startActivity(intent);
                break;
            case R.id.aaaaa:
                Intent intent2 = new Intent(MainActivity.this, NextActivity.class);
                startActivity(intent2);
                break;
        }
    }

    // onStop() : 더이상 엑티비티가 사용자에게 보여지지 않을 때 불립니다.
    // 기존에 존재하는 데이터를 저장합니다.

    protected void onStop() {
        super.onStop();

    }
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        edit.setText("");
       /* AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
        //다이얼로그의 내용을 설정합니다.
        alertdialog.setTitle("공지");
        alertdialog.setMessage("작성중인 내용으로 진행 하시겠습니까?");

        //확인 버튼
        alertdialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) findViewById(R.id.text);
                EditText editText2 = (EditText) findViewById(R.id.text2);
                String text = editText.getText().toString();
                String text2 = editText2.getText().toString();
                // 데이타를저장합니다.
                SharedPreferences prefs = getSharedPreferences("PrefName", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_MY_PREFERENCE, text); //putString 안에는 키와 값으로 이루어져있다.
                editor.putString(KEY_MY_PREFERENCE, text2);
                editor.commit();
                //  myadapter.notifyDataSetChanged();
                //확인 버튼이 눌렸을 때 토스트를 띄워줍니다.
               // Toast.makeText(MainActivity.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //취소 버튼
        alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //취소 버튼이 눌렸을 때 토스트를 띄워줍니다.
                //Toast.makeText(Record.this, "취소", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = alertdialog.create();
        alert.show();*/
    }


}