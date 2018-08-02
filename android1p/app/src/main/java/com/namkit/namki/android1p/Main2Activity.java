package com.namkit.namki.android1p;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    FragmentTabHost tabHost;
    private static final String TAB1 = "tab1";
    private static final String TAB2 = "tab2";
    private static final String TAB3 = "tab3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec(TAB1).setIndicator("TAB1"), FragmentOne.class ,null);
        tabHost.addTab(tabHost.newTabSpec(TAB2).setIndicator("TAB2"), FragmentTwo.class ,null);
        tabHost.addTab(tabHost.newTabSpec(TAB3).setIndicator("TAB3"), FragmentThree.class ,null);
        //setIndicator는 탭의 라벨로 이해하면 됨 그리고 프래그먼트 3개 추가~
        //탭의 이름을 문자형 대신에 이미지를 넣을떄는 view를 만들어 넣어주면 됩니다.

    }
}
