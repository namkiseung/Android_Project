package com.namkit.namki.a0502samplehandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


/*public class MainActivity extends AppCompatActivity {
/**
 * Handler의 메시지 전달 함수의 종류
 * 1. Handler.sendEmptyMessage(int what)
 * 2. Handler.sendMessage(Message msg)
 * */
/*    public static final int SEND_INFORMATION = 0;
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

        //Thread Start 버튼을 클릭했을 때 Thread 시작
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = new Thread();
                thread.start();
            }
        });
        //스탑 버튼 클릭하면 Thread를 정지시키며 handler에게 SEND_STOP 메시지를 보냄
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(SEND_STOP);
            }
        });
    }
    final Handler handler = new Handler();

    public void handleMessage(Message msg){   //
        switch (msg.what){
            case SEND_INFORMATION:
                Log.d("handleMessage", "handleMessage안에 msg는 어떤정보를 담나?"+msg);
                Log.d("handleMessage", "handleMessage안에 msg.arg1는 어떤정보를 담나?"+msg.arg1);
                Log.d("handleMessage", "handleMessage안에 Integer.toString(msg.arg1)는 어떤정보를 담나?"+Integer.toString(msg.arg1));
                Log.d("handleMessage", "handleMessage안에 msg.obj는 어떤정보를 담나?"+msg.obj);
                textView.setText(Integer.toString(msg.arg1) + msg.obj);
                break;
            case SEND_STOP:
                thread.stopThread();
                textView.setText("Thread가 중지됨.");
                break;
        }
    }
    class Thread extends java.lang.Thread{
        boolean stopped = false;
        int i=0;

        public Thread(){
            stopped = false;
        }
        public void stopThread() { stopped = true; }

        public void run(){
            super.run();

            while(stopped == false){
                i++;
            }
            //메시지 얻어오기
            Message message = handler.obtainMessage();
            //메시지 ID 설정
            message.what = SEND_INFORMATION;
            //메시지 내용 설정(int)
            message.arg1 = i;
            //메시지 내용 설정(Object)
            String information = new String("초 째 Thread 동작 중입니다");
            message.obj = information;

            //메시지 전
            handler.sendMessage(message);

            try{
                sleep(500); //0.5초 씩 딜레이 부여
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}*/