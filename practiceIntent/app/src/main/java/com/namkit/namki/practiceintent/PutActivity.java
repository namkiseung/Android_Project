package com.namkit.namki.practiceintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by namki on 2018-02-25.
 */
//뷰의 ID값을가져와서 final로 객체 생성
//버튼에 리스너 붙혀서 click이벤트 만들고
//클릭했을때 edit에 적은 내용을 가져와서 새로선언한 문자열 변수에 저장하고
    //Data객체 생성해서 Data 인스턴스 파라미터에 새로선언했던 변수를 보낸다
    //그리고 인텐트 객체에 명시적으로 어떤 액티비티에 보낼지 정하고
    //putExtra를 이용하여 인텐트 객체 파라미터에 " "텍스트와  정보를 가진 Data객체를  보내고 startActivity!!!!!!
public class PutActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.put_activity);

        final EditText etName = (EditText)findViewById(R.id.et_name);
        final EditText etAge = (EditText)findViewById(R.id.et_age);

        Button button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                //Data객체 생성
                Data data = new Data(name, age);

                //Intent 객체에 Data객체 저장
                Intent getintent = new Intent(PutActivity.this, GetActivity.class);
                getintent.putExtra("보냅니다", data);

                //GetActivity로 전환
                startActivity(getintent);

            }
        });
    }

}
