package com.namkit.namki.a0505handlerbasicincrease;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * sendEmptyMessage는 내용없는 메시지
 * sendMessage는 실제 Message를 보낸다.
 * */

/**
 * 생성자로 메시지를 만드는 것과 obtain메서드를 사용하는 것의 큰 차이는
 * obtain 메서드는 재활용 될 객체를 pool에서 뽑아온다.*/

/**
 * Message.obtain()과 Handler.obtainMessage() 메서드의 차이점은,
 * Handler.obtainMessage()의 경우 자동으로 TargetHandler가 호출하는 Handler로 정해진다는 것이 차이점입니다.
 * 즉, Message.obtain() 메소드를 사용하면 Target Handler를 지정해 줘야 한다는 말과도 같겠죠.

 Handler.sendMessage() 메소드와 Message.sendToTarget() 메소드,
 Handler.obtainMassage() 메소드와 Message.obtain() 메소드,
 이 중에서 마음에 드는 것으로 사용 하시면 되겠습니다.*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView tv;
Button btn_start, btn_stop;
int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.text000);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_stop=(Button)findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }
    @Override
    protected void onStop(){
        super.onStop();
        handler.removeMessages(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                //핸들러 호출
                handler.sendEmptyMessage(0); //이 메서드가 호출되면 해당 핸들러에 핸들메시지가 동작이 된다.
                break;
            case R.id.btn_stop:
                //핸들러 정지
                handler.removeMessages(0);
                break;
        }
    }
    Handler handler = new Handler(){  //핸들 메시지라는 것을 오버라딩 해줘야 한다 (단축키 ctrl+o)
        @Override
        public void handleMessage(Message msg) {
            //1초 간격으로 카운터를 증가 시키자
            handler.sendEmptyMessageDelayed(0, 1000);  //핸들러가 호출 될 때마다 1초 딜레이 시킨다
            count++;//카운트를 증가 시키고
            tv.setText(""+count);//카운터가 증가시키는 것을 tv에 띄어준다
        }
    };
}
