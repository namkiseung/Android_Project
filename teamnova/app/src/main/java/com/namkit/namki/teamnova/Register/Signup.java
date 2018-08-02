package com.namkit.namki.teamnova.Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.namkit.namki.teamnova.Login.Login;
import com.namkit.namki.teamnova.R;

public class Signup extends AppCompatActivity implements View.OnClickListener{
    EditText userId, userpw, userrepwd, username, userage, useremail;
    Button btn_idEqual;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userId = (EditText)findViewById(R.id.etNewId);
        userpw = (EditText)findViewById(R.id.etNewPwd);
        userrepwd = (EditText)findViewById(R.id.etNewRePwd);
        username = (EditText)findViewById(R.id.etNewName);
        userage = (EditText)findViewById(R.id.etNewAge);
        useremail = (EditText)findViewById(R.id.etNewEmail);
        btn_idEqual = (Button) findViewById(R.id.btn_idEqual);
        btn_idEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringId = userId.getText().toString(); //Edit에 입력한 Id값
                SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);  // 쉐어프리페런스 파일 만듬
                String userDetails = preferences.getString(stringId + "TEAM_MEMBER_ID", ""); //MYPREFS 파일안에 저장값을 가져와서 (userDetial)와 공유
                SharedPreferences.Editor editor = preferences.edit();

                if (!stringId.equals("")) {
                    if(stringId.length()>3 && stringId.length()<11) { //4글자 부터 최대 10글자

                        if (stringId.equals(userDetails)) {  //아이디에 입력한 값과  그 값이 이미 저장되어 있는지 확인
                            Toast.makeText(Signup.this, "이미 가입된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Signup.this, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            check = true;
                        }
                    }else{
                        Toast.makeText(Signup.this, "아이디는 4글자 이상 10글지 이하로 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Signup.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageButton button = (ImageButton)findViewById(R.id.btn_finish);
        button.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.btn_signupcommit);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_finish:
                finish();
                break;
            case R.id.btn_signupcommit:
                //비밀번호 맞는지 확인구문 예시
                if(userId.getText().toString().equals("")||userpw.getText().toString().equals("")||userrepwd.getText().toString().equals("")||username.getText().toString().equals("")||userage.getText().toString().equals("")||useremail.getText().toString().equals("")) {
                    Toast.makeText(Signup.this, "아직 입력되지 않은 회원정보가 있습니다", Toast.LENGTH_SHORT).show();
                }else{
                    if(check==true) { // 사용자가 EditText ID에 입력한 텍스트가 이미 존재하는 아이디와 같은지 비교

                        if (userpw.getText().toString().equals(userrepwd.getText().toString())) {
                            if (userpw.length() > 3 && userpw.length() < 9) {
                                SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", Context.MODE_PRIVATE);  // 쉐어프리페런스 파일 만듬
                                String newUser = userId.getText().toString();

                                String newPwd = userpw.getText().toString();
                                String newRePwd = userrepwd.getText().toString();
                                String newName = username.getText().toString();
                                String newAge = userage.getText().toString();
                                String newEmail = useremail.getText().toString();
                                String tempName = newName; // 이름을 키값으로 아이디를 뽑기 위해서 tempName이라는 변수 생성

                                SharedPreferences.Editor editor = preferences.edit(); //수정 기능 사용 선언
                                editor.putString(newUser+"TEAM_MEMBER_ID", newUser);
                                editor.putString(newUser+"TEAM_MEMBER_PW", newPwd);
                                editor.putString(newUser+"TEAM_MEMBER_NAME", newName);
                                editor.putString(tempName+"NAMETOID", newUser);//이름을 키 / 아이디가 속성
                                editor.putString(newUser+"TEAM_MEMBER_AGE", newAge);
                                editor.putString(newUser+"TEAM_MEMBER_EMAIL", newEmail);
                                editor.commit();

                                Intent intent = new Intent(Signup.this, Login.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(Signup.this, "비밀번호를 4~8자리로 입력해주세요", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "ID 중복체크를 해주세요", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();

    }
    @Override
    public void onStop(){
        super.onStop();

    }

}
