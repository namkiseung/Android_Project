package com.namkit.namki.teamnova;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.namkit.namki.teamnova.Login.Login;

public class SpalshStart extends AppCompatActivity {
    AnimationDrawable animationDrawable;//애니메이션 객체 가자
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_start);
        RelativeLayout transcolor = (RelativeLayout) findViewById(R.id.sp_Layout); //움직이게 할 배경 가져오기
        ImageView logo = (ImageView) findViewById(R.id.sp_logo); //이미지뷰 가져오기
        TextView text = (TextView) findViewById(R.id.sp_text);  //텍스트뷰 가져오기

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.loadingsplash); //스플래쉬 xml가져오기
        logo.startAnimation(animation); //이미지뷰 애니메이션실행
        text.startAnimation(animation); //텍스트뷰 애니메이션 실행



        animationDrawable = (AnimationDrawable) transcolor.getBackground(); //배경색 객체에 애니메이션 선언(배경색 데이터 가져오기)
        animationDrawable.setEnterFadeDuration(3000);//시작 페이드
        animationDrawable.setExitFadeDuration(4000);//끝 페이드
        animationDrawable.start();//애니메이션 동작 시작


        final Intent intent = new Intent(this, Login.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


        ImageView dynimic = (ImageView)findViewById(R.id.sp_logo);
        dynimic.setImageDrawable(getResources().getDrawable(R.drawable.dynimic_logo));
        AnimationDrawable animation2 = (AnimationDrawable)dynimic.getDrawable();

        if(hasFocus) {
            animation2.start();
        } else {
            animation2.stop();
        }
    }
}

/**
 * imageView.setBackgroundResource(R.drawable.animation);
 AnimationDrawable animation = (AnimationDrawable)imageView.getBackground();
 animation.start();

[에러]
 java.lang.ClassCastException: android.graphics.drawable.BitmapDrawable cannot be cast to android.graphics.drawable.AnimationDrawable

 2-1) java.lang.ClassCastException: android.graphics.drawable.BitmapDrawable cannot be cast to android.graphics.drawable.AnimationDrawable
 2-2) BitmapDrawable을 AnimationDrawable에 캐스팅에러
 2-3) 검색 결과 (animation.xml에서 AnimationDrawable을 정의하면이 작업가능 확인)
 2-4) 검색결과 (View.getBackground ()는 백그라운드 XML 자원에서로드 된 드로어 블을 리턴)
 2-5) setImageDrawable과 getDrawable을 사용하면 효과
 2-6) 에러해결 (잘이해는 되지 않지만,

[해결]
 imageView.setImageDrawable(getResource().getDrawable(R.drawable.animation);
 AnimationDrawable animation = (AnimationDrawable)imageView.getDrawable();
 animation.start();
 * */