package com.namkit.namki.a0501samplethread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 스레드를 이용해 프로그레스바를 보여주는 방법에 대해 알 수 있습니다.
 * 별도로 만든 스레드에서 메인 스레드를 접근할 때 핸들러를 사용해야 한다는 것을 알 수 있습니다.*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /** 다이얼로그 생성에서 프로그레시브바를 구분하기 위한 인자 */
	private static final int PROGRESSBAR_DLG_LARGE = 1;
	private static final int PROGRESSBAR_DLG_MID = 2;
	private static final int PROGRESSBAR_DLG_SMALL = 3;
	private static final int PROGRESSBAR_DLG_STATIC = 4;
	private static final int PROGRESSBAR_DLG_SPINNER = 5;

    private Button mBtnProgressDlg;
    private Button mBtnSpinner;
    private Button mBtnLarge;
    private Button mBtnMid;
    private Button mBtnSmall;
    private Button mBtnStick;

    private AsyncTask<Integer, String, Integer> mProgressDlg;
    private ProgressBar mProgressLarge;
    private ProgressBar mProgressMid;
    private ProgressBar mProgressSmall;
    private ProgressBar mProgressStick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 객체 설정
        mBtnProgressDlg = (Button) findViewById(R.id.btnProgressDialog);
        mBtnLarge = (Button)findViewById(R.id.btnProgressLarge);
        mBtnMid = (Button)findViewById(R.id.btnProgressMid);
        mBtnSmall = (Button)findViewById(R.id.btnProgressSmall);
        mBtnStick = (Button)findViewById(R.id.btnProgressStick);

        // 프로그레시브바 설정
        mProgressLarge = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressMid = (ProgressBar) findViewById(R.id.progressBar2);
        mProgressSmall = (ProgressBar) findViewById(R.id.progressBar3);
        mProgressStick = (ProgressBar) findViewById(R.id.progressBar4);

        // 클릭이벤트 설정
        mBtnProgressDlg.setOnClickListener(this);
        mBtnLarge.setOnClickListener(this); // 진행바 큰것
        mBtnMid.setOnClickListener(this); // 진행바 중간
        mBtnSmall.setOnClickListener(this);// 진행바 작은것
        mBtnStick.setOnClickListener(this); // 막대형 진행바

        // 진행바를 숨긴다
        mProgressLarge.setVisibility(ProgressBar.GONE);
        mProgressMid.setVisibility(ProgressBar.GONE); // 진행바 중간
        mProgressSmall.setVisibility(ProgressBar.GONE);// 진행바 작은것
        mProgressStick.setVisibility(ProgressBar.GONE); // 막대형 진행바

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProgressDialog:
                mProgressDlg = new ProgressDlgSample(MainActivity.this).execute(100);
                break;

            case R.id.btnProgressLarge:
                mProgressLarge.setVisibility(ProgressBar.VISIBLE);
                mProgressLarge.setIndeterminate(true);
                mProgressLarge.setMax(100);

                break;

            case R.id.btnProgressMid:
                mProgressMid.setVisibility(ProgressBar.VISIBLE);
                mProgressMid.setIndeterminate(true);
                mProgressMid.setMax(100);
                break;

            case R.id.btnProgressSmall:
                mProgressSmall.setVisibility(ProgressBar.VISIBLE);
                mProgressSmall.setIndeterminate(true);
                mProgressSmall.setMax(100);
                break;

            case R.id.btnProgressStick:
                mProgressStick.setVisibility(ProgressBar.VISIBLE);
                mProgressStick.setIndeterminate(true);
                mProgressStick.setMax(100);
                break;

            default:
                break;
        }
    }
}

/**
 *  ProgressBar bar;    //프로그레스바
 TextView textView;
 boolean isRunning = false; //움직이는것에 대한 체크

 ProgressHandler handler;//메인스레드의 UI에 접근하기위해 핸들러 사용
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 bar = (ProgressBar) findViewById(R.id.progress);
 textView = (TextView) findViewById(R.id.textView);

 handler = new ProgressHandler();
 }
 protected void onStart(){
 super.onStart();

 bar.setProgress(0); //bar 프로그레스바 객체에 0 세팅
 Thread thread1 = new Thread(new Runnable() {
 @Override
 public void run() {
 try{
 for (int i = 0; i < 20 && isRunning; i++) {
 Thread.sleep(100);

 Message msg = handler.obtainMessage(); // Message 타입의 msg객체에 핸들러를 이용하여 메시지를 얻는다
 handler.sendMessage(msg); //핸들러 객체에 메시지를 보낸다 내용은 메시지타입의 msg변수
 }
 }catch (Exception e){
 Log.e("MainActivity", "프로세싱 메시지 안에 예외 : " + e);
 }
 }
 });
 isRunning = true; //움직였다면 true 변환
 thread1.start(); //thread1 작동시작
 }
 protected void onStop(){
 super.onStop();

 isRunning = false; //화면이 보이지 않으면 동작 그만
 }

 public class ProgressHandler extends Handler{ //핸들러 클래스 상속
 public void handleMessage(Message msg){
 bar.incrementProgressBy(5); //뭔지 모르겠다

 if(bar.getProgress() == bar.getMax()){
 textView.setText("Done"); // Done 표시 프로그레스바 가득 찼을때
 }else{
 textView.setText("작업중"+ bar.getProgress()); // Done 표시 프로그레스바 가득 찼을때
 }
 }
 }
 */