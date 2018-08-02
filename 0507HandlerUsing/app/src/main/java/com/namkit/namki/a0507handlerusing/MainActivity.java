package com.namkit.namki.a0507handlerusing;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    private static final int SEND_THREAD_INFOMATION = 0;
    private static final int SEND_THREAD_STOP_MESSAGE = 1;

    private SendMassgeHandler mMainHandler = null;
    private CountThread mCountThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            Bundle bundle = savedInstanceState.getParcelable("countering");
            if(bundle!=null){
                String data = bundle.getString("counter", "");
                tv_Count.setText(data);
               }
        }
        // 레이아웃 설정
        setLayout();
        // 메인 핸들러 생성
        mMainHandler = new SendMassgeHandler();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread_start:
                mCountThread = new CountThread();
                mCountThread.start();
                break;
            case R.id.btn_thread_stop_message:
                mMainHandler.sendEmptyMessage(SEND_THREAD_STOP_MESSAGE);
                break;
            default:
                break;
        }
    }
    // Handler 클래스
    class SendMassgeHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SEND_THREAD_INFOMATION:
                    tv_Count.setText("현재시간 = " + msg.arg1 + "\n" +
                            "index = " + msg.arg2 + " 인 \n" + msg.obj);
                    break;
                case SEND_THREAD_STOP_MESSAGE:
                    mCountThread.stopThread();
                    tv_Count.setText("Count Thread가 중지 되었습니다.");
                    break;

                default:
                    break;
            }
        }
    };
    // Thread 클래스
    class CountThread extends Thread implements Runnable {
        private boolean isPlay = false;
        public CountThread() {
            isPlay = true;
        }
        public void isThreadState(boolean isPlay) {
            this.isPlay = isPlay;
        }
        public void stopThread() {
            isPlay = !isPlay;
        }
        @Override
        public void run() {
            super.run();
            int i = 0;
            while (isPlay) {
                i++;
                // 메시지 얻어오기
                Message msg = mMainHandler.obtainMessage();

                // 메시지 ID 설정
                msg.what = SEND_THREAD_INFOMATION;

                // 메시지 정보 설정 (int 형식)
                msg.arg1 = Integer.valueOf(getNowDateTime());

                // 메시지 정보 설정2 (int 형식)
                msg.arg2 = i;

                // 메시지 정보 설정3 (Object 형식)
                String hi = new String("Count Thared 가 동작하고 있습니다.");
                msg.obj = hi;

                mMainHandler.sendMessage(msg);
                // 1초 딜레이
                try { Thread.sleep(1000); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
    public String getNowDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("HHmmss").format(date);
    }
    /**
     * Layout
     */
    private TextView tv_Count = null;
    public void setLayout() {
        tv_Count = (TextView) findViewById(R.id.tv_count);
    }
    @Override
    protected void onSaveInstanceState(Bundle saveBundle) {
        super.onSaveInstanceState(saveBundle); // 반드시 호출해 주세요.
        Bundle bundle = new Bundle();
        String a = tv_Count.getText().toString();
        bundle.putString("counter",a);
        saveBundle.putParcelable("countering", bundle);
        // 추가로 자료를 저장하는 코드는 여기에 작성 하세요.
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String b = savedInstanceState.getString("counter","");
        tv_Count.setText(b);
        // 추가로 자료를 복원하는 코드는 여기에 작성하세요.
    }
}






