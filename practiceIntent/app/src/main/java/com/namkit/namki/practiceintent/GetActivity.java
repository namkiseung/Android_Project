package com.namkit.namki.practiceintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by namki on 2018-02-25.
 */
/*인텐트 객체를 이용해 Data객체 받기
Data객체 선언하고 getSeriallzableExtra파라미터에 키값 일치
해당 뷰 객체 만들어 아이디 값 받아오고
해당 뷰 객체에 setText 선언하고 파라미터에 데이터객체의 속성접근*/

public class GetActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_activity);
        
        //Data 객체를 받을 Intent설정
        Intent getintent = getIntent();
        Data data = (Data)getintent.getSerializableExtra("보냅니다");

        TextView txtName = (TextView)findViewById(R.id.txt_name);
        TextView txtAge = (TextView)findViewById(R.id.txt_age);
        
        //Data TextView에 적용
        txtName.setText("입력한 이름 : " + data.name);
        txtAge.setText("입력한 나이 : " + String.valueOf(data.age));
    }
}
