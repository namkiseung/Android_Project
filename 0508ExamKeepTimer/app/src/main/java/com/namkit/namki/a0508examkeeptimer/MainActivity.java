package com.namkit.namki.a0508examkeeptimer;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private static final long START_TIME_IN_MILLIS = 600000;

private TextView mTextViewCountDown;
private Button mButtonStartPause;
private Button mButtonReset;

private CountDownTimer mCountDownTimer;

private boolean mTimerRunning;
private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
private long mEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonStartPause = findViewById(R.id.button_start_pause);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();
                   // System.out.println("@@@@@@@@@@@@@@"+System.currentTimeMillis()+"초@@@@@@@@@@@@@시스템시간");
                   // System.out.println("#############mEndTime"+mEndTime+"초###########################");
                   //System.out.println("mTimeLeftInMillis"+mTimeLeftInMillis+"초");
                }else{
                    startTimer();
                   // System.out.println("@@@@@@@@@@@@@@"+System.currentTimeMillis()+"초@@@@@@@@@@@@@시스템시간");
                   //System.out.println("#############mEndTime"+mEndTime+"초###########################");
                   // System.out.println("mTimeLeftInMillis"+mTimeLeftInMillis+"초");
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
        updateCountDownText();
    }
    private void startTimer(){
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
              //  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+System.currentTimeMillis()+"초@@@@@@@@@@@@@시스템시간");
              //  System.out.println("###################################################################mEndTime"+mEndTime+"초###########################");
              //  System.out.println("====================================================================mTimeLeftInMillis"+mTimeLeftInMillis+"초");
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                updateButtons();
            }
        }.start();
        mTimerRunning = true;
        updateButtons();
    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateButtons();
    }
    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }
    private void updateCountDownText(){
        int minutes = (int)(mTimeLeftInMillis / 1000) / 60;
        int seconds = (int)(mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateButtons(){
        if(mTimerRunning){
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        }else{
            mButtonStartPause.setText("Start");
            if(mTimeLeftInMillis<1000){
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }else{
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if(mTimeLeftInMillis<START_TIME_IN_MILLIS){
                mButtonReset.setVisibility(View.VISIBLE);
            }else{
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    @Override
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
    }
}
