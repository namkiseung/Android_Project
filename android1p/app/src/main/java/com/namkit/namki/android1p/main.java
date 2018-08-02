package com.namkit.namki.android1p;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티를 생성시마다 호출되기때문에 반드시선언
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//중요한점은 onCreat에서 setContentView()를 호출해야 액티비티의 API를 정의할 수 있다.
        //Log.d("내가확인", "onCreat");//처음 App실행시 로그 남기기
        final Button button1 = (Button)findViewById(R.id.loginbtn);
        final Button button2 = (Button)findViewById(R.id.joinbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main.this, MainActivity.class); //명시적 인텐트 선언
                startActivity(intent);//액티비티 실행
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"안녕하세요",  Toast.LENGTH_LONG).show();
            }
        });
    }

    /*//종료하기 다이얼로그 로직
    public void onBackPressed(){
        AlertDialog.Builder ad = new AlertDialog.Builder(main.class);
        ad.setTitle("종료");
        ad.setMessage("어플을 종료하시겠습니까?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        ad.setNegativeButton("No", null);
        ad.show();
    }*/
}