package com.namkit.namki.a0601fragmentanimation;

import android.app.FragmentManager;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 하울꺼에 섞기
 * 제목 : Create Background Animation Like Instagram Login Android Tutorials
URL : https://www.youtube.com/watch?v=xTf_oJGz_9A
 *
 * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;//애니메이션가자
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout button1 = (LinearLayout)findViewById(R.id.button1);
        LinearLayout button2 = (LinearLayout)findViewById(R.id.button2);
        LinearLayout button3 = (LinearLayout)findViewById(R.id.button3);
        LinearLayout button4 = (LinearLayout)findViewById(R.id.button4);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.myFrameLay); //프레임레이아웃
        animationDrawable = (AnimationDrawable)frameLayout.getBackground(); //프레임 그리고 애니메이션 선언
        animationDrawable.setEnterFadeDuration(5000);//시작 페이드
        animationDrawable.setExitFadeDuration(2000);//끝 페이드
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        animationDrawable.start();//시작가자


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim2, R.animator.exit_anim2).replace(R.id.myFrameLay, new MyFragment1()).commit(); //리플레이스로 바꾸는 사이에 애니메이션 코드 넣으면 된다
                break;  //입력되는 화면애니메이션과, 사라지는 화면 애니메이션 2군데 코드 넣어야한다
            case R.id.button2:
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim2, R.animator.exit_anim2).replace(R.id.myFrameLay, new MyFragment2()).commit(); //리플레이스로 바꾸는 사이에 애니메이션 코드 넣으면 된다
                break;
            case R.id.button3:
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim).replace(R.id.myFrameLay, new MyFragment3()).commit(); //리플레이스로 바꾸는 사이에 애니메이션 코드 넣으면 된다
                break;
            case R.id.button4:
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim).replace(R.id.myFrameLay, new MyFragment4()).commit(); //리플레이스로 바꾸는 사이에 애니메이션 코드 넣으면 된다
                break;
        }
    }
}


/*  액티비티에서 플래그먼트 접근하는 방법
*        btn_activity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainFragment mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.ll_fragment);
                mainFragment.changeFragmentTextView("호호호");
            }
        });
        replaceFragment();
    }

    public void replaceFragment()
    {
        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll_fragment, mainFragment);
        fragmentTransaction.commit();
    }

    public void changeText(String text)
    {
        tv_activity.setText(text);
    }
}


출처: http://itpangpang.xyz/309 [ITPangPang]
* */