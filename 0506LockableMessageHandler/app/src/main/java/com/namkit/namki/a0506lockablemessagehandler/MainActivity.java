package com.namkit.namki.a0506lockablemessagehandler;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/**
 * 어플리케이션, 특히 UI 프로그래밍을 하다 보면 메세지 처리 라는 녀석을 피할 수 없습니다.
 예를 들어, 로딩 중에 '로딩 중' 이라고 알려 주는 다이얼로그를 표시하고 싶다고 할 때,
 단순히 스레드로만 처리를 하면 서로 엉켜서 다이얼로그가 제대로 표시 되지 않을 때가 많지요.
 그래서 아래와 같이 살짝 딜레이를 주게 되면 엉키는 것을 조금이나마 완화 시킬 수 있습니다*/
    Button button;
    TextView text;
    ProgressBar circle_bar;

    int count2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textView);
        circle_bar = (ProgressBar) findViewById(R.id.progressBar);
        circle_bar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountTask count = new CountTask();
                count.execute();
            }
        });
    }

    class CountTask extends AsyncTask<Integer, Integer, Void> {  //AsyncTask가 제네릭 클래스이기 때문에 래퍼값으로 int 대신에 Integer다 혹은 double 사용
        //AsyncTask<Integer, Integer, Void> 인자는 꼭 3개
        @Override
        protected Void doInBackground(Integer... params) {

            for(int i=0; i<100;i++){ //10000번 돌면서
                if(i%10 == 0){
                    try {
                        publishProgress(new Integer(i)); //10번마다 TextView에 출력
                        Thread.sleep(100);
                    }catch (Exception e){

                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            text.setText(text.getText().toString()+"\n"+values[0].intValue()+"번 count했습니다.");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            circle_bar.setVisibility(View.VISIBLE);
            text.setText(text.getText().toString()+"\n"+"Count를 시작하겠습니다.");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            text.setText(text.getText().toString()+"\n"+"Count가 끝났습니다.");
            circle_bar.setVisibility(View.GONE);
        }
    }
}

/**예를 들어 봅시다.
 onDown 이벤트가 발생 한 뒤에 onSingleTapUp 이벤트가 발생 하는 상황을 살펴 보겠습니다.
 보통 onDown - onSingleTapUp 이벤트가 발생 했다는 것은 한번 터치 했을 경우입니다.
 그렇다는 것은 터치가 되는 대상이 존재 한다는 말이겠죠.
 그럼, 이제 여기서 좀 더 세부적으로 살펴 보겠습니다.

 터치가 되는 대상이 어떤 하얀색 사각형 '들' 이라고 했을때,
 onDown 이벤트 시에는 어떤 사각형이 선택이 되었는지 검색을 하며,
 onSingleTapUp 이벤트 시에는 선택된 사각형을 빨간색으로 만드는 일을 할 것입니다.
 연결 동작으로 보면 터치 했을 때 선택된 사각형이 빨간색으로 변하게 되겠죠?

 그런데 만약에 onDown 이벤트 시에 어떤 사각형이 선택이 되었는지 미처 찾기도 전에
 onSingleTapUp 이벤트가 발생한다면 어떻게 될까요?
 네... 물론 아무것도 변하지 않겠죠.
 그렇게 되면 결과적으로 onSingleTapUp 이벤트는 아무것도 안하고 그냥 흘러가는 메세지가 됩니다.

 물리적으로는 onDown 이벤트와 onSingleTapUp 이벤트 둘 다 발생 했지만,
 논리적으로 봤을땐... 하나는 실패한 이벤트입니다.
 마치 하드웨어에서 인터럽트가 많아지면 나중에 들어온 인터럽트는 무시당하는 경우와 비슷하죠.

 그렇다면 위와 같은 경우에는 흘러가는 메세지를 그냥 보내야만 할까요?
 잡아둘 방법은 없을까요?

 [메세지를 흘러가지 못하도록 막자!]

 이제부터 본격적으로 제가 하고싶은 이야기를 할 것입니다.
 그림이 없어서 복잡할지도 모르겠지만, 최대한 잘 설명해 보겠습니다.
 위에서 언급했던 onDown - onSingleTapUp 이벤트를 다시 한번 봅시다.

 선택된 사각형을 찾았을 때 onRectangleFounded 메소드가 호출 되며,
 onSingleTapUp 이벤트 안에서 선택된 사각형의 색을 바꾼다고 합시다.

 그렇다면, 동작이 원하는데로 이루어지기 위해서는 아래와 같은 순서로 진행 되어야 합니다.
 onDown - onRectangleFounded - onSingleTapUp - (선택된 사각형의 색이 변함) 하지만 만약 사각형을 찾는 시간이 onSingleTapUp 이벤트 보다 느리다면,
  onDown - onSingleTapUp (색을 바꿀 사각형이 없음) - onRectangleFounded - (변화 없음)

 onSingleTapUp 보다는
 onRectangleFounded의 이벤트를 먼저 처리 해야 한다는 것을 다시 한번더 정리해 봤습니다.
 항상 첫 번째의 가장 좋은 경우가 일어나면 괜찮은데,
 두 번째의 경우가 일어나지 말라는 법은 없습니다. 보장 할 수 없죠.
 그래서 한번 생각해 봤습니다. Lock을 걸면 되지 않을까...?

 Lock을 걸어본다면 흐름은 아마 아래와 같을 것입니다.
 onDown with Lock - onSingleTapUp (Lock에 의해 메세지가 실행 대기 상태가 됨)
 - onRectangleFounded with Unlock (대기 하고 있던 onSingleTapUp 메세지가 실행 됨)
 onSingleTapUp 메세지는 Lock에 의해 Queue에 저장이 되고,
 Unlock시에 Queue에 쌓인 메세지들을 실행 하면 되겠거니 하고 생각해봤습니다* */
