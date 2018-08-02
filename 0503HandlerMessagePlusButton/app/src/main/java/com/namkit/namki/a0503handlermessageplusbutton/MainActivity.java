package com.namkit.namki.a0503handlermessageplusbutton;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mainValue = 0;    // 메인스레드의 정수
    int backValue = 0;    // 서브스레드의 점수
    TextView mainText;     //xml의 뷰 위젯(메인스레드 값 표시)
    TextView backText;     //xml의 뷰 위젯(서브스레드 값 표시)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView)findViewById(R.id.mainvalue);     // 메인스레드 영역을 표시할 뷰위젯의 id 호출
        backText = (TextView)findViewById(R.id.backvalue);     // 서브스레드 영역을 표시할 뷰위젯의 id 호출

        // 스레즈 생성하고 시작
        BackThread thread = new BackThread();   //BackThread 클래스 인스턴화!!!! (해당 스레드에서 하는일은 백그라운드 스레드 값을 1씩 증가시킨다.)
        thread.setDaemon(true);  //데몬스레드를 true 함으로서 프로세스가 죽으면 스레드도 죽게 설정
        thread.start();

    } //end onCreate()

    // 버튼을 누르면 mainValue 증가
    public void mOnClick(View v){
        mainValue++;
        mainText.setText("MainValue:" + mainValue);
    }

    class BackThread extends Thread{
        @Override
        public void run() {
            while(true){
                backValue++;
                // 메인에서 생성된 Handler 객체의 sendEmpryMessage 를 통해 Message 전달
                handler.sendEmptyMessage(0);   //sendEmptyMessage과 sendMessage로 이 핸들러에 메시지를 보낼 수 있다.
                //매개변수 (what이란 메시지를 구분할때 쓰인다)
                //sendEmptyMessage의 경우 sendEmptyMessage(int what)으로, what외에 arg1, arg2, odj 등을 보낼 수 있다.(what의 값에 따라 어떤 행동을 할 것인지 정해주기)

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        } // end run()
    } // end class BackThread

    // '메인스레드' 에서 Handler 객체를 생성한다.
    // Handler 객체를 생성한 스레드 만이 다른 스레드가 전송하는 Message나 Runnable 객체를
    // 수신할수 있다.
    // 아래 생성된 Handler 객체는 handlerMessage() 를 오버라이딩 하여
    // Message 를 수진합니다.
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){   // Message id 가 0 이면
                backText.setText("BackValue:" + backValue); // 메인스레드의 UI 내용 변경
            }
        }
    };

} // end class



