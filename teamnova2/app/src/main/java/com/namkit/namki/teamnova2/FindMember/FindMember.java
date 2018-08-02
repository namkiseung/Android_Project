package com.namkit.namki.teamnova2.FindMember;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.namkit.namki.teamnova2.Login.Login;
import com.namkit.namki.teamnova2.R;

import java.lang.ref.WeakReference;

public class FindMember extends AppCompatActivity {
    SharedPreferences preferences;
    TextView displayId, displaypw, displayEmail;
    Button Gobutton, Idbutton, Pwbutton;
    int falsecount=0, falsecount2=0; //카운트세기 (버튼비활성화)
    EditText etname, etid, etemail;
    String aa, aaa;
    int intenabled, intenabled2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_member);
        preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);

        displayId = (TextView)findViewById(R.id.FindIdDisplay);
        displaypw = (TextView)findViewById(R.id.FindPwDisplay);
        displayEmail = (TextView)findViewById(R.id.FindEmailDisplay);

        Idbutton = (Button)findViewById(R.id.btn_Find_ID);
        Pwbutton = (Button)findViewById(R.id.btn_Find_PW);
        Gobutton = (Button)findViewById(R.id.btn_MoveToLogin);

        etname = (EditText)findViewById(R.id.etFindName);
        etid = (EditText)findViewById(R.id.etFindId_id);
        etemail = (EditText)findViewById(R.id.etFindEmail_Email);

        Idbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUserName = etname.getText().toString(); //이름에 입력된 내용
                if(!newUserName.equals("")){ //빈칸인지 확인

                    String getUserTo = preferences.getString(newUserName+"NAMETOID", "정확히 입력하세요"); //저장되있던 이름 to 값 //이름을 키 / 아이디가 속성   결국 이름적으면 아이디가 나온다
                    String 입력한이름으로아이디가져와서그아이디로아이디찾기 = preferences.getString(getUserTo+"TEAM_MEMBER_ID", "정확히 입력하세요"); //저장되있던 ID 값
                    String getUserEmail = preferences.getString(getUserTo+"TEAM_MEMBER_EMAIL", "정확히 입력하세요"); //저장되있던 이메일 값

                    if(입력한이름으로아이디가져와서그아이디로아이디찾기 == "정확히 입력하세요"){
                        falsecount++;
                        displayId.setText("");
                        displayEmail.setText("");
                        Toast toast = Toast.makeText(FindMember.this, falsecount+"회 / 3 이상 틀리지 마세요.", Toast.LENGTH_SHORT);
                        int xOffset = 50;
                        int yOffset = 50;
                        toast.setGravity(Gravity.CENTER, xOffset, yOffset);
                        toast.show();
                    }
                    else if(입력한이름으로아이디가져와서그아이디로아이디찾기.equals(getUserTo)){
                        falsecount=0;
                    }
                    try{aa = getUserTo.substring(getUserTo.length()-2, getUserTo.length());//문자열 잘라서 aa에 입력 그리고, aa를 '*'로 치환
                        aaa = getUserTo.replaceAll(aa, "**");}catch (StringIndexOutOfBoundsException e){}
                    displayId.setText(aaa+" ("+getUserTo.length()+" 자리 )");
                    displayEmail.setText("E-mail : "+getUserEmail);
                    etname.setText("");

                    if(falsecount == 3){
                        Idbutton.setEnabled(false);
                        final Handler handler = new MyHandler(FindMember.this);

                        Runnable task = new Runnable() {
                            @Override
                            public void run() {
                                intenabled=30;
                                while (intenabled>0){
                                    try {
                                        Thread.sleep(800);
                                        --intenabled;
                                        handler.sendEmptyMessage(1);

                                        Message msg = Message.obtain();
                                        msg.what = 1;

                                        handler.sendMessage(msg);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                falsecount = 0;
                            }
                        };
                        Thread th = new Thread(task);
                        th.start();
                    }else if(falsecount > 3){
                        falsecount = 0;
                    }
                }else {
                    Toast.makeText(FindMember.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Pwbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUserId = etid.getText().toString(); //아이디에 입력된 내용
                String newUserEmail = etemail.getText().toString(); //이메일에 입력된 내용

                String getUserId = preferences.getString(newUserId+"TEAM_MEMBER_ID", "정확히 입력하세요"); //저장되있던 ID 값
                String getUserPw = preferences.getString(newUserId+"TEAM_MEMBER_PW", "정확히 입력하세요"); //저장되있던 비밀번호 값
                String getUserEmail = preferences.getString(newUserId+"TEAM_MEMBER_EMAIL", "정확히 입력하세요"); //저장되있던 이메일 값

                if(getUserId == "정확히 입력하세요" || newUserEmail == "정확히 입력하세요"){
                    falsecount2++;
                    displayId.setText("");
                    displaypw.setText("");

                    Toast toast = Toast.makeText(FindMember.this, falsecount2+"회 / 3 이상 틀리지 마세요.", Toast.LENGTH_SHORT);
                    int xOffset = 50;
                    int yOffset = 50;
                    toast.setGravity(Gravity.CENTER, xOffset, yOffset);
                    toast.show();
                }
                else if("정확히 입력하세요" != getUserId|| "정확히 입력하세요" != getUserEmail){
                    falsecount2=0;
                }

                if(!getUserId.equals("")||!newUserEmail.equals("")){
                    etid.setText("");
                    etemail.setText("");

                    if(newUserEmail.equals(getUserEmail)||falsecount2 == 3){ //일단은 입력한 이메일이 입력한 아이디로 회원가입했던 이메일과 같은지 비교!!!!!!!저장된 아이디와 입력이 같고, 그 아이디를 키값으로한 이메일과 적은이메일이 맞는지 확인
                        displaypw.setText(getUserPw); //ID 값 리턴
                        etid.setText("");
                        etemail.setText("");
                        if(falsecount2 == 3){
                            falsecount2=0;
                            Pwbutton.setEnabled(false);
                            final Handler handler = new MyHandler(FindMember.this);

                            Runnable task2 = new Runnable() {
                                @Override
                                public void run() {
                                    intenabled2=30;
                                    while (intenabled2>0){
                                        try {
                                            Thread.sleep(800);
                                            --intenabled2;
                                            handler.sendEmptyMessage(2);

                                            Message msg = Message.obtain();
                                            msg.what = 2;
                                            handler.sendMessage(msg);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            };
                            Thread th2 = new Thread(task2);
                            th2.start();
                        }
                    }else{
                    }
                }else{
                    Toast.makeText(FindMember.this, "아이디와 이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindMember.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private static class MyHandler extends Handler{
        private final WeakReference<FindMember> mFindMember;

        private MyHandler(FindMember findMember) {
            mFindMember = new WeakReference<FindMember>(findMember);
        }

        @Override
        public void handleMessage(Message msg) {
            FindMember findMember = mFindMember.get();
            if(mFindMember != null) {
                switch (msg.what) {
                    case 1:
                        findMember.etname.setText("정보찾기 일시금지");
                        findMember.Idbutton.setText(findMember.intenabled+"초");

                        if(findMember.intenabled==0){
                            findMember.Idbutton.setEnabled(true);
                            findMember.falsecount=0;
                            findMember.etname.setText("");
                            findMember.displayId.setText("");
                            findMember.displayEmail.setText("");
                            findMember.Idbutton.setText("아이디 찾기");
                        }
                        break;
                    case 2:
                        findMember.etid.setText("정보찾기 일시금지");
                        findMember.etemail.setText("정보찾기 일시금지");
                        findMember.Pwbutton.setText(findMember.intenabled2+"초");

                        if(findMember.intenabled2==0){
                            findMember.Pwbutton.setText("비밀번호 찾기");
                            findMember.Pwbutton.setEnabled(true);
                            findMember.falsecount2=0;
                            findMember.etid.setText("");
                            findMember.displaypw.setText("");
                            findMember.etemail.setText("");
                        }
                        break;
                }
            }
        }
    }
    protected void onStop(){
        super.onStop();

    }
    protected void onStart(){
        super.onStart();

    }
}
