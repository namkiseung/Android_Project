package com.namkit.namki.teamnova2.MainActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.namkit.namki.teamnova2.Menu1_Wakeup.Wakeup;
import com.namkit.namki.teamnova2.Menu2_Daily.Daily;
import com.namkit.namki.teamnova2.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener{
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton call, sms, web, map;
    String currentid;
    AnimationDrawable animationDrawable;//애니메이션 객체 가자
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout transcolor = (DrawerLayout)findViewById(R.id.drawerLayout); //움직이게 할 배경 가져오기
        animationDrawable = (AnimationDrawable)transcolor.getBackground(); //배경색 객체에 애니메이션 선언(배경색 데이터 가져오기)

        animationDrawable.setEnterFadeDuration(3000);//시작 페이드
        animationDrawable.setExitFadeDuration(4000);//끝 페이드
        animationDrawable.start();//애니메이션 동작 시작
        mainclock();
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter viewadapter = new ImageAdapter(this);
        viewPager.setAdapter(viewadapter);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {//(첫번째 파라미터 : 현재 존재하는 View 객체들 중에서 위치가 변경되고 있는 View들)(두번째 파라미터 : 각 View 들의 상대적 위치( 0.0 ~ 1.0 : 화면 하나의 백분율))
                //(현재 Page의 위치가 조금이라도 바뀔때마다 호출되는 메소드)Pager가 Page를 변경할 때 특정 작업을 수행하고 싶을 때 PageTransformer 설정. 여기서는 ViewPager가 Page를 변경할 때 애니메이션이 되도록 설정

                //주의할 것은 현재 Page가 이동하면 동시에 옆에 있는 Page(View)도 이동함. 첫번째와 마지막 Page 일때는 총 2개의 View가 메모리에 만들어져 잇음.
                //나머지 Page가 보여질 때는 앞뒤로 2개의 View가 메모리에 만들어져 총 3개의 View가 instance 되어 있음.(ViewPager 한번에 1장의 Page를 보여준다면 최대 View는 3개까지만 만들어지며, 나머지는 메모리에서 삭제됨.-리소스관리 차원.)
                float normalizedposition = Math.abs( 1 - Math.abs(position));
                page.setAlpha(normalizedposition);  //View의 투명도 조절
                page.setScaleX(normalizedposition/2 + 0.5f); //View의 x축 크기조절
                page.setScaleY(normalizedposition/2 + 0.5f); //View의 y축 크기조절
                page.setRotationY(position * 80);   //View의 Y축(세로축) 회전 각도
            }
        });

        //로그인한 사람의 ID를 새로운 키의 값에 넣어준다 => 나오는 값은 현재 유저 아이디
        preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        currentid = preferences.getString("currentdisplayId","");

        //로그인한 사람의 아이디
       /* SharedPreferences prefs = getSharedPreferences("NowUser", 0);
        SharedPreferences.Editor logineditor = prefs.edit();
        logineditor.putString("currentdisplayId", currentid);
        logineditor.commit();*/

        //툴바 생성
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //툴바 리스너 붙히기
        toolbar.setOnMenuItemClickListener(this);

        //플로팅메뉴(+버튼)
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.floatingActionMenu);
        call = (FloatingActionButton) findViewById(R.id.floatingActionItem_Call);
        sms = (FloatingActionButton) findViewById(R.id.floatingActionItem_SMS);
        web = (FloatingActionButton) findViewById(R.id.floatingActionItem_Web);
        map = (FloatingActionButton) findViewById(R.id.floatingActionItem_Map);

        call.setOnClickListener(this);
        sms.setOnClickListener(this);
        web.setOnClickListener(this);
        map.setOnClickListener(this);

    //xml버튼아이디값 받아와서 리스너 붙혀주기
        ImageButton homebutton = (ImageButton) findViewById(R.id.btn_home_activity);
        homebutton.setOnClickListener(this);
        ImageButton wakeupbutton = (ImageButton) findViewById(R.id.btn_wakeup_activity);
        wakeupbutton.setOnClickListener(this);
        ImageButton recordbutton = (ImageButton) findViewById(R.id.btn_daily_activity);
        recordbutton.setOnClickListener(this);
        ImageButton newsbutton = (ImageButton) findViewById(R.id.btn_news_activity);
        newsbutton.setOnClickListener(this);
    }


    //옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //클릭에 대해 뷰 아이디를 받아와서 처리
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_activity:
                Intent moveonhome = getIntent();
                moveonhome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(moveonhome);
                break;
            case R.id.btn_wakeup_activity:
               Intent moveonwakeup = new Intent(MainActivity.this, Wakeup.class);
               startActivity(moveonwakeup);
                break;
            case R.id.btn_daily_activity:
                Intent moveondaily = new Intent(MainActivity.this, Daily.class);
                startActivity(moveondaily);
                break;
            case R.id.btn_news_activity:
              // Intent moveonnews = new Intent(MainActivity.this, News.class);
              //  startActivity(moveonnews);
                break;
            case R.id.floatingActionItem_Call: //암시적인텐트 전화
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01072552316"));
                startActivity(call);
                break;
            case R.id.floatingActionItem_SMS: //암시적인텐트 문자
                Intent sms = new Intent(Intent.ACTION_SEND);
                sms.setType("text/plain");
                String sendtext = "내용을 입력하세요.";
                sms.putExtra(Intent.EXTRA_TEXT, sendtext);
                Intent chooser = Intent.createChooser(sms, "친구에게 공유하기");
                startActivity(sms);
                break;
            case R.id.floatingActionItem_Web: //암시적인텐트 웹사이트
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.teamnova.co.kr"));
                startActivity(web);
                break;
            case R.id.floatingActionItem_Map: //암시적인텐트 맵
                Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=팀노바"));
                startActivity(map);
                break;
        }
    }

    //현재시간 명령한 포멧으로 세팅
    private void currentclock() {//현재 시각 가져와서 포멧에 맞춰 setText하기
        //현재시간 띄우기
        long now = System.currentTimeMillis(); //현재시간을 불러온다
        Date date = new Date(now);//Date객체 생성후 현재시간 값 부여
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd \n HH:mm:ss");
        String formatDate = sdfNow.format(date);
        TextView dateNow;
        dateNow = (TextView) findViewById(R.id.text_dateNow);
        dateNow.setText(formatDate);
    }


    //    메인에서 back버튼 누르면 종료 메시지 알림으로 띄우기
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("로그아웃 하시겠습니까?");
        alert.setIcon(R.drawable.ic_launcher);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*SharedPreferences prefs = getSharedPreferences("NowUser", 0);
                SharedPreferences.Editor logineditor = prefs.edit();
                logineditor.remove("currentdisplayId");
                logineditor.apply();
                finish();*/
                /*preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("currentdisplayId");
                editor.apply();*/
                finish();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create();
        alert.show();
        //super.onBackPressed();
    }
    public void onPause() {
        super.onPause();
        Log.i("Main Acitivty", "onPause()");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.i("Main Acitivty", "onStart()");
    }
    @Override
    public void onStop() {
        super.onStop();

    }
    public void mainclock(){
        //시간스레드
        Thread t = new Thread() {//현재 시간을 set 해서 1초 마다 스레드
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
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_main_info:

                Toast.makeText(this, "1클릭1", Toast.LENGTH_SHORT).show();
                //openOptionsDialog();
                break;
            case R.id.item_main_monitor:
                // Toast.makeText(getApplicationContext(), "클릭2", Toast.LENGTH_SHORT).show();
              //  Intent second = new Intent(MainActivity.this, MainSecond.class);
              //  startActivity(second);
                //openOptionsDialog();
                break;
            case R.id.item_main_community:
                Log.d("진입","옵션메뮤@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                //Toast.makeText(this, "하이", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_main_preferences:
                Toast.makeText(this, "하이", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_main_byebye:
                exitOptionsDialog();
                Toast.makeText(this, "그동안 이용해주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main Acitivty", "onResume()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Main Acitivty", "onStart()");
    }
    private void openOptionsDialog() { //컨텍스트 메뉴에서 아이템 하나 클릭했을때 실행되는 메서드
        new AlertDialog.Builder(this)
                .setTitle("내정보")
                .setMessage("정보창")
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
                                Toast.makeText(MainActivity.this, "openOptionsDialog()", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
    }
    private void exitOptionsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("계정삭제")
                .setMessage("회원님의 데이터가 삭제되오니, 신중하게 생각하시길 바랍니다.")
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
//                                Toast.makeText(MainActivity.this, "exitOptionsDialog() OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
                                preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                currentid = preferences.getString("currentdisplayId","");
                                editor.remove(currentid+"TEAM_MEMBER_ID");
                                editor.remove(currentid+"TEAM_MEMBER_PW");
                                editor.remove(currentid+"TEAM_MEMBER_NAME");
                                editor.remove(currentid+"NAMETOID");
                                editor.remove(currentid+"TEAM_MEMBER_AGE");
                                editor.remove(currentid+"TEAM_MEMBER_EMAIL");
                                editor.apply();
                                Toast.makeText(MainActivity.this, "로그인 후 이용해 주세요.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).show();
    }

}

