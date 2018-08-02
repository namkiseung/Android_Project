package com.namkit.namki.examfunction;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class innermemory extends AppCompatActivity {
    EditText edit;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innermemory);

        edit= (EditText)findViewById(R.id.edit);
        text= (TextView)findViewById(R.id.text);
    }
    //xml 온클릭설정에 대한 콜백함수 메서드
    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.btn_save: //내부 저장소에 파일 저장하기
                String data=edit.getText().toString(); //에디트 텍스트에서 텍스트 얻어오기
                edit.setText("");

                try{
                    //FileOutputStream 객체생성, 파일명 "data.txt" 새로운 텍스트 추가하기 모드
                    FileOutputStream fos = openFileOutput("data.txt", Context.MODE_APPEND);

                    PrintWriter writer = new PrintWriter(fos);
                    writer.println(data);
                    writer.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                //소프트 키보드 없애기
                InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                break;
            case R.id.btn_load: //file 에서 읽어오기
                StringBuffer buffer= new StringBuffer();

                try {
                    //FileInputStream 객체생성, 파일명 "data.txt"
                    FileInputStream fis=openFileInput("data.txt");
                    BufferedReader reader= new BufferedReader(new InputStreamReader(fis));
                    String str=reader.readLine();//한 줄씩 읽어오기
                    while(str!=null){
                        buffer.append(str+"\n");
                        str=reader.readLine();
                    }
                    text.setText(buffer.toString());
                                    } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

        }
    }


}
