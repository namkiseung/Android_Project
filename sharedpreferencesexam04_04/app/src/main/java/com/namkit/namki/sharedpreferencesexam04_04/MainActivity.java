package com.namkit.namki.sharedpreferencesexam04_04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
EditText name, age;
SharedPreferences myprefs;
Button save, reset;
    String nameText;
    int ageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button)findViewById(R.id.button);
        reset = (Button)findViewById(R.id.button1);

        myprefs = getPreferences(MODE_PRIVATE);
        init();
    }

    private void init(){
        name = (EditText)findViewById(R.id.editTextName);
        age = (EditText)findViewById(R.id.editTextAge);
        readPreferences();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                nameText = name.getText().toString();
                ageText = Integer.parseInt(age.getText().toString());
                SharedPreferences.Editor editor = myprefs.edit();
                editor.putString("keyname", nameText);
                editor.putInt("keyage", ageText);
                editor.commit();

                break;
            case R.id.button1:
                SharedPreferences.Editor editor1 = myprefs.edit();
                editor1.clear();
                editor1.commit();
                readPreferences();
                break;
        }
    }
    //저장된 사람의 데이터를 읽고 표시합니다.
    public void readPreferences(){
        //데이터를 읽어야한다 -> 저장된 사람과 편집 텍스트로 표시
        //내 "Sharedpreferences" 변수를 사용해야한다
        String st1 = myprefs.getString("keyname","");
        name.setText(st1); //에디터 텍스트에 띄움

        int vall = myprefs.getInt("ketage",0);
        age.setText(String.valueOf(vall));
    }
}