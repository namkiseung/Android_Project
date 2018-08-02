package com.namkit.namki.a0504threadwhandler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {
    EditText EditText;
    TextView Count_TextView;
    Button Button;
    int inputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText = (EditText) findViewById(R.id.EditText);
        Count_TextView = (TextView) findViewById(R.id.Count_TextView);
        Button = (Button) findViewById(R.id.Button);
    }

    public void Button_Click(View v){
        String input = EditText.getText().toString();
        Count_TextView.setText(input);
        if(input.equals("")){
            Toast.makeText(this, "공백입니다", Toast.LENGTH_SHORT).show();
        }else{
            inputNumber = Integer.parseInt(input);

            if(inputNumber==0){
                Toast.makeText(this, "0은 입력할수 없습니다", Toast.LENGTH_SHORT).show();
                return;
            }
            Button.setEnabled(false);
            final Handler handler = new MyHandler(this);
            Runnable task = new Runnable(){
                @Override
                public void run(){
                    while(inputNumber > 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}

                        --inputNumber;
                        /**
                         * sendEmptyMessage은 단순한 int형 What을 전달하기 때문에
                         * Message객체의 생성이 필요가 없습니다
                         */
                        handler.sendEmptyMessage(1); //핸들러에 메시지 send 가능

                        /**
                         * sendMessage는 message객체를 넘겨주며,
                         * 이때 what의 값, arg1, arg2등 int형 값을 줄수도 있고
                         * intent등의 객체 전체를 넘길수도 있다 (message.obj = 겍체)
                         */
                        Message message= Message.obtain(); //.obtain을 이용해서 sendMessage할 때 필요한 msg 만들 수 있다.
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) { //여기에서 TextView에 직접 접근 가능
            MainActivity activity = mActivity.get();
            if (activity != null) {
                /**
                 * 넘겨받은 what값을 이용해 실행할 작업을 분류합니다
                 */
                if(msg.what==1){
                    Log.d("What Number : ", "What is 1");
                }else if(msg.what==2){
                    Log.d("What Number : ", "What is 2");
                }

                activity.Count_TextView.setText(""+activity.inputNumber);
                if(activity.inputNumber==0){
                    Toast.makeText(activity, "카운트가 완료되었습니다", Toast.LENGTH_SHORT).show();
                    activity.Button.setEnabled(true);
                }
            }
        }
    }
}

/*

public class MainActivity extends Activity {
	EditText EditText;
	TextView Count_TextView;
	Button Button;
	int inputNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EditText = (EditText) findViewById(R.id.EditText);
		Count_TextView = (TextView) findViewById(R.id.Count_TextView);
		Button = (Button) findViewById(R.id.Button);
	}

	public void Button_Click(View v){
		String input = EditText.getText().toString();
		Count_TextView.setText(input);

		if(input.equals("")){
			Toast.makeText(this, "공백입니다", Toast.LENGTH_SHORT).show();
		}else{
			inputNumber = Integer.parseInt(input);

			if(inputNumber==0){
				Toast.makeText(this, "0은 입력할수 없습니다", Toast.LENGTH_SHORT).show();
				return;
			}
			Button.setEnabled(false);

			final Handler handler = new Handler(){
				@Override
				public void handleMessage(Message msg){
					/**
					 * 넘겨받은 what값을 이용해 실행할 작업을 분류합니다
					 */
				/*	if(msg.what==1){
                            Log.d("What Number : ", "What is 1");
                            }else if(msg.what==2){
                            Log.d("What Number : ", "What is 2");
                            }

                            Count_TextView.setText(""+inputNumber);
                            if(inputNumber==0){
                            Toast.makeText(MainActivity.this, "카운트가 완료되었습니다", Toast.LENGTH_SHORT).show();
                            Button.setEnabled(true);
                            }
                            }
                            };
                            Runnable task = new Runnable(){
public void run(){
        while(inputNumber > 0){
        try {
        Thread.sleep(1000);
        } catch (InterruptedException e) {}

        --inputNumber;
        /**
         * sendEmptyMessage은 단순한 int형 What을 전달하기 때문에
         * Message객체의 생성이 필요가 없습니다
         */
      //  handler.sendEmptyMessage(1);

        /**
         * sendMessage는 message객체를 넘겨주며,
         * 이때 what의 값, arg1, arg2등 int형 값을 줄수도 있고
         * intent등의 객체 전체를 넘길수도 있다 (message.obj = 겍체)
         */
       /* Message message= Message.obtain();
        message.what = 2;
        handler.sendMessage(message);
        }
        }
        };
        Thread thread = new Thread(task);
        thread.start();
        }
        }
        }

*/