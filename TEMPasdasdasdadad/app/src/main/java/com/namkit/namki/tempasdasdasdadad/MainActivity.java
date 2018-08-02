package com.namkit.namki.tempasdasdasdadad;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int SEND_INFORMATION = 0;
    public static final int SEND_STOP = 1;

    TextView textView;
    Button startButton;
    Button stopButton;

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        startButton = (Button) findViewById(R.id.button);
        stopButton = (Button) findViewById(R.id.button2);

        // Thread Strat 버튼을 클릭했을 때 Thread를 시작
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread();
                thread.start();
            }
        });

        // Thread 를 Stop 시키며 handler에게 SEND_STOP 메시지를 보냄
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(SEND_STOP);
            }
        });
    }

    final Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case SEND_INFORMATION:
                    textView.setText(Integer.toString(msg.arg1) + msg.obj);
                    break;

                case SEND_STOP:
                    thread.stopThread();
                    textView.setText("Thread 가 중지됨.");
                    break;

                default:
                    break;
            }


        }
    };

    class Thread extends java.lang.Thread {

        boolean stopped = false;
        int i = 0;

        public Thread(){
            stopped = false;
        }

        public void stopThread() {
            stopped = true;
        }

        @Override
        public void run() {
            super.run();

            while(stopped == false) {
                i++;

                // 메시지 얻어오기
                Message message = handler.obtainMessage();

                // 메시지 ID 설정
                message.what = SEND_INFORMATION;

                // 메시지 내용 설정 (int)
                message.arg1 = i;

                // 메시지 내용 설정 (Object)
                String information = new String("초 째 Thread 동작 중입니다.");
                message.obj = information;

                // 메시지 전
                handler.sendMessage(message);

                try {
                    // 1초 씩 딜레이 부여
                    sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
   /* @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        updateButtons();
        if(mTimerRunning){
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimerRunning =  prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateButtons();

        if(mTimerRunning){
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
            if(mTimeLeftInMillis < 0){
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateButtons();
            }else{
                startTimer();
            }
        }
    }*/

