package com.namkit.namki.examvibrator;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{
    Vibrator vide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vide = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                vide.vibrate(300);
            break;
            case R.id.button2:
                long[] pattern = { 0, 200, 100, 300, 100 };
                /*-1(음수)은 1번 반복 하겠다는 뜻입니다

                구글 개발자 사이트 원문 : the index into pattern at which to repeat, or -1 if you don't want to repeat.
                즉 0과 양수를 넣으면 long[]에서 index부터 시작하겠다는 뜻이 됩니다

                예를 들면 vide.vibrate(pattern, 2); 로 하게 되면
                56줄(long[])에서 index 2번값은 "200"입니다
                그러므로 200, 400, 100만 무한 반복이 됩니다

                또 예를 들면 vide.vibrate(pattern, 3); 로 하게 되면
                56줄(long[])에서 index 3번값은 "400"입니다
                그러므로 400, 100만 무한 반복이 됩니다
                무한 반복을 종료하려면 vide.cancel();을 사용하면 됩니다*/

                 vide.vibrate(pattern, -1);
                break;
        }
    }
}
