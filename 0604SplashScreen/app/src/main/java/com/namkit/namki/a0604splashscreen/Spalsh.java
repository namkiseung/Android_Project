package com.namkit.namki.a0604splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
/**
글 제목 : Splash Screen with Transition Animation in Android Studio
URL : https://www.youtube.com/watch?v=h_hTuaEpc-8
 */
public class Spalsh extends AppCompatActivity {
private TextView tv;
private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        tv = (TextView)findViewById(R.id.tv);
        iv = (ImageView)findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);

        final Intent intent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
/**
 * 동작 순서
 * 1. 처음에 A 액티비티를 띄운다
 * 2. A 액티비티에는 이미지와 글씨가 있다(ActionBar가 안보인다)
 * 3. A 액티비티가 투명상태에서 서서히 보이다가 완전 선명해지고 자동꺼진다
 * 4. Main액티비티가 등장한다.
 * */
/**
 * -클래스 이름 변경 (MainActivity -> Spalsh)
 -activity파일에 릴레티브 레이아웃을 메인으로 생성
 -activity파일 메인레이아웃 안에 리니어레이아웃 생성
 -activity파일에 이미지뷰 , 텍스트뷰 생성
 -activity파일에 뷰들의 색상 때문에 color.xml 과 style.xml 수정
 -이미지뷰에 기본이미지 추가
 -ActionBar 스타일 -> NoActionBar로 변경
 -'anim'이라는 리소스 디렉터리 파일생성 type은 Values
 -'anim' 폴더 안에 mytransition.xml 생성
 -mytransition.xml 안에 Alpha 엘리먼트 생성

 -class파일에서 이미지와 텍스트뷰 id받아오기
 -Animation 객체 만들기
 -Animation 객체에 AnimationUtils.loadAnimation(this, 리소스) 부여하기
 -각뷰 객체에에 startAnimation 설정하기
 -새로운 Empty액티비티 설정하기
 -스레드 객체 만들기
 -인텐트 객체에 메인 액티비티로 이동한다고 선언
 -스레드 로직안에 sleep이 끝이나면 startActivity로 보내고, 해당 액티비티는 finish() 한다 [finally]
 */
