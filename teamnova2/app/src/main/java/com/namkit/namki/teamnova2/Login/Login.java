package com.namkit.namki.teamnova2.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.namkit.namki.teamnova2.FindMember.FindMember;
import com.namkit.namki.teamnova2.MainActivity.MainActivity;
import com.namkit.namki.teamnova2.R;
import com.namkit.namki.teamnova2.Register.Signup;

public class Login extends Activity implements View.OnClickListener {
    //private BackPressCloseHandler backPressCloseHandler;

    EditText etId, etPwd;
    Button button, button2, button3;
    String tempID, tempPW;
    SharedPreferences preferences;
    CheckBox memoryId;
    ProgressBar circle_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memoryId = (CheckBox)findViewById(R.id.Auto_MemoId);
        button = (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.btn_signup);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.btn_signupcommit);
        button3.setOnClickListener(this);
        etId = (EditText) findViewById(R.id.edtext_id);
        etPwd = (EditText) findViewById(R.id.edtext_pwd);
        circle_bar = (ProgressBar) findViewById(R.id.progressBar);
        circle_bar.setVisibility(View.GONE);
        etId.setText("");
        etPwd.setText("");
        checkSharedPreferences();

        preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        memoryId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if(buttonView.getId() == R.id.Auto_MemoId){
                 if(isChecked){
                     SharedPreferences.Editor editor = preferences.edit();
                     editor.putString("basiccheck", "true");
                     editor.putString("basicid",etId.getText().toString());
                     //editor.putString("basicpw",etPwd.getText().toString());
                     editor.apply();
                 }else{
                     SharedPreferences.Editor editor = preferences.edit();
                     editor.putString("basiccheck", "false");
                     editor.putString("basicid","");
                     //editor.putString("basicpw","");
                     editor.apply();
                 }
             }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                tempID = etId.getText().toString();
                tempPW = etPwd.getText().toString();

                String displayId = preferences.getString(tempID + "TEAM_MEMBER_ID", "");
                String displaypwd = preferences.getString(tempID + "TEAM_MEMBER_PW", "");

                if (!tempID.equals("") || !tempPW.equals("")) {//아이디와 비번 공백이 넘어올때의 처리
                    if (tempID.equals(displayId) && tempPW.equals(displaypwd) || tempID.equals("user")) {//아이디와 비번이 맞는지
                        if (tempID.equals(displayId) || tempID.equals("user")) { //가입이 되어있는지 확인
                            SharedPreferences.Editor editor = preferences.edit();
                            Intent login = new Intent(Login.this, MainActivity.class);
                            editor.putString("currentdisplayId", displayId);
                            editor.commit();
                            CountTask count = new CountTask();  //AsyncTask상속받은 클래스를 사용하자
                            count.execute();//count 객체 실행
                            etId.setText("");
                            etPwd.setText("");
                            startActivity(login);
                        } else {//가입이 안되었을때
                            Toast.makeText(Login.this, "가입된 사용자가 아닙니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "ID와 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "ID와 PW 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_signup:
                Intent signup = new Intent(Login.this, Signup.class);
                startActivity(signup);

                break;
            case R.id.btn_signupcommit:
                Intent signfind = new Intent(Login.this, FindMember.class);
                startActivity(signfind);
                break;
        }
    }

   /* @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
        backPressCloseHandler = new BackPressCloseHandler(this);//뒤로가기 버튼 2번클릭 객체
        backPressCloseHandler.onBackPressed();
    }*/

    class CountTask extends AsyncTask<Integer, Integer, Void> { //AsyncTask가 제네릭 클래스이기 때문에 래퍼값으로 int 대신에 Integer다 혹은 double 사용 / AsyncTask<Integer, Integer, Void> 인자는 꼭 3개  / doInBackground을 제외한 나머지 3개는 MainThread에서 처리하게 된다.
        @Override
        protected Void doInBackground(Integer... integers) { //Thread를 처리하고 싶은 코드를 작성하는 곳

            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) { //새로운 Thread가 진행 중 실행하고 싶은 경우
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() { //doInBackground가 실행되기 전에 실행하고 싶은 경우
            super.onPreExecute();
            circle_bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) { //doInBackground의 수행이 끝난 뒤 실행하고 싶은경우
            super.onPostExecute(aVoid);
            circle_bar.setVisibility(View.GONE);
        }
    }

    //로그인시 아이디 저장
    private void checkSharedPreferences(){
        preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String stockcheck = preferences.getString("basiccheck", "false");
        String stockId =  preferences.getString("basicid", "");
        //String stockPw = preferences.getString("basicpw", "");
        etId.setText(stockId);
       // etPwd.setText(stockPw);

        if(stockcheck.equals("true")){ //만약
            memoryId.setChecked(true);
        }else {
            memoryId.setChecked(false);
        }
    }
}
/*
class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;
    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide(); //토스트창 메서드
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }
    public void showGuide() {
        Toast toast = Toast.makeText(activity, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}*/
