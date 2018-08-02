package com.namkit.namki.a0605screenupdownanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
제목 : Welcomescreen with upToDown animation in android studio
URL : https://www.youtube.com/watch?v=pznCs--BtJA
 */
public class welcomeActivity extends AppCompatActivity {
LinearLayout l1, l2;
Button btnsub;
Animation uptodown, downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnsub = (Button)findViewById(R.id.buttonsub);
        l1 = (LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);
    }
}
/**
 * NOACTIONBAR 만들고
 xml파일 만져보자
 위 아래로 레이아웃나눈다
 drawable리소스 안에 draw파일리소스를 만든다 'buttonstyle'
 이제 뷰에 id를 지정해보자

 그리고 메인클래스 파일에 넘어와서 id를 이용해 객체를 만든다
 anim 폴더 생성 그리고 'uptodown'애니메이션을 설정할 리소스디렉터리 만든다
 해당 파일안에 Y좌표 움직임을 얼마동안 동작한다고 선언하고
 클래스에서 해당 리소스를 불러온다
 레이아웃 객체에 setAnimation 해준다

 * */
