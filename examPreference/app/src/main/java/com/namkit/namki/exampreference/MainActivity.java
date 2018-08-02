package com.namkit.namki.exampreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText input_ID, input_PW;
    CheckBox Auto_LogIn;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_ID = (EditText) findViewById(R.id.input_ID);
        input_PW = (EditText) findViewById(R.id.input_PW);
        Auto_LogIn = (CheckBox) findViewById(R.id.Auto_LogIn);

        setting = getSharedPreferences("setting", 0);
        editor= setting.edit();
//자동로그인을 설정하면 껏다켜도 유지되어야 하는 조건
        if(setting.getBoolean("Auto_Login_enabled", false)){ //처음 실행시 설정 값 없음. 기본값 false반환
            input_ID.setText(setting.getString("ID", "")); //ID와 PW 불러와서 에디트텍스트에 setText로 적용
            input_PW.setText(setting.getString("PW", ""));
            Auto_LogIn.setChecked(true);
        }
//체크박스를 선택할때마다 호출할 리스너
        Auto_LogIn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    String ID = input_ID.getText().toString();
                    String PW = input_PW.getText().toString();

                    editor.putString("ID", ID);
                    editor.putString("PW", PW);
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                }else{
                    /**
                     * remove로 지우는것은 부분삭제
                     * clear로 지우는것은 전체 삭제 입니다
                     */
//					editor.remove("ID");
//					editor.remove("PW");
//					editor.remove("Auto_Login_enabled");
                    editor.clear();
                    editor.commit();//이코드가 없다면 실제로 변경된 내용이 반영되지 않는다
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

