package com.namkit.namki.android1p;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TabHost;
        import android.widget.TextView;
        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * Created by namki on 2018-02-23.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티를 생성시마다 호출되기때문에 반드시선언
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//중요한점은 onCreat에서 setContentView()를 호출해야 액티비티의 API를 정의할 수 있다.
/*
        //탭들 폼을 TabHost의 객체를 가져온다.
        //TabActivtiy를 확장했기 때문에 getTabHost() 사용
        //Activtiy를 확장했다면 (TabHost)findViewById를 사용해야한다.
            TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
        //각각 탭이 지닐 기능을 정할 TabSpec 객체 등록
            TabHost.TabSpec spec = tabHost.newTabSpec("홈"); //spec란 객체를 만들고 탭을 구별할 태그 등록
        //각각 탭이 어떤 Activity를 보여줄지 정보를 담은 Intent 객체 등록
            Intent intent = new Intent().setClass(MainActivity.this, Main2Activity.class);
            spec.setContent(intent); //탭에 intent등록
            tabHost.addTab(spec); //TabHost에 첫 번째 탭 등록

        //spec의 매소드들을 나열해서 코딩 가능
            intent = new Intent().setClass(this, SubActivity2.class);
            spec = tabHost.newTabSpec("Tab2").setIndicator("TAB2",
                    res.getDrawable(android.R.drawable.ic_menu_edit)).setContent(intent);
            tabHost.addTab(spec);

            intent = new Intent().setClass(this, SubActivity3.class);
            spec = tabHost.newTabSpec("Tab3").setIndicator("TAB3",
                    res.getDrawable(android.R.drawable.ic_menu_help));.setContent(intent);
            tabHost.addTab(spec);*/


Button btn2 = (Button)findViewById(R.id.btn2);
btn2.setOnClickListener(this);

        //시간스레드
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                currentclock();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
           }
        };
        t.start();
        creatTab();
    }
    private void currentclock(){
        //현재시간 띄우기
        long now = System.currentTimeMillis(); //현재시간을 불러온다
        Date date = new Date(now);//Date객체 생성후 현재시간 값 부여
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(date);
        TextView dateNow;
        dateNow = (TextView)findViewById(R.id.dateNow);
        dateNow.setText(formatDate);
    }
    private void creatTab(){
        TabHost ts = (TabHost) findViewById(R.id.tabHost);
        ts.setup(); //TabHost 레이아웃의 아이디와 레이아웃 태그 설정

        TabHost.TabSpec ts1 = ts.newTabSpec("홈");
        ts1.setContent(R.id.tab1);
        ts1.setIndicator("홈");
        Log.d("", "홈버튼 클릭");
        ts.addTab(ts1);


        TabHost.TabSpec ts2 = ts.newTabSpec("기상인증");
        ts2.setContent(R.id.tab2);
        ts2.setIndicator("기상인증");
        Log.d("", "기상인증 버튼 클릭");
        ts.addTab(ts2);

        TabHost.TabSpec ts3 = ts.newTabSpec("개발일지");
        ts3.setContent(R.id.tab3);
        ts3.setIndicator("개발일지");
        Log.d("", "개발일지 클릭");
        ts.addTab(ts3);

        TabHost.TabSpec ts4 = ts.newTabSpec("수업기록");
        ts4.setContent(R.id.tab4);
        ts4.setIndicator("수업기록");
        Log.d("", "수업기록");
        ts.addTab(ts4);

        TabHost.TabSpec ts5 = ts.newTabSpec("URL스크랩");
        ts5.setIndicator("URL스크랩");
        Log.d("", "URL스크랩 클릭");
        ts5.setContent(R.id.tab5);
        ts.addTab(ts5);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    public void onStop()
    {
        super.onStop();
    }
    @Override
    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}

