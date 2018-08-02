package com.namkit.namki.preferregister04_07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText)findViewById(R.id.etName); //Edit뷰(ID입력)를 쓸 수 있게 한다 => etName을 통해서
        final EditText etPassword = (EditText)findViewById(R.id.etPassword); //Edit뷰(PW입력)를 쓸 수 있게 한다 => etPassword를 통해서
        Button btnLogin = (Button)findViewById(R.id.btnLogin); //로그인 사용에 필요한 버튼View를 쓸 수 있게 한다 => btnLogin을 통해서
        Button btnRegister = (Button)findViewById(R.id.btnRegister); //회원가입 접속에 사용할 버튼View를 쓸 수 있게 한다 => btnRegister를 통해서

        btnLogin.setOnClickListener(new View.OnClickListener() { //(로그인 기능)btnLogin 객체를 클릭했을때 일어나는 동작을 서술한다.
            @Override
            public void onClick(View view) {
                String user = etName.getText().toString(); //etName에 있는 text를 [user 변수]에 담는다
                String password = etPassword.getText().toString(); //etPassword에 있는 text를 [password 변수]에 담는다

                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE); // 저장공간인 MYPREFS 파일을 만든다

                String userDetails = preferences.getString(user + password + "data", "Username or Password is Incorrect"); //MYPREFS 파일안에 저장값을 가져와서 (userDetial)와 공유
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display", userDetails);
                editor.commit();

                Intent displayScreen = new Intent(MainActivity.this, DisplayScreen.class);
                startActivity(displayScreen);
            }
        });
btnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent registerScreen = new Intent(MainActivity.this, Register.class);
        startActivity(registerScreen);
    }
});
    }
}
