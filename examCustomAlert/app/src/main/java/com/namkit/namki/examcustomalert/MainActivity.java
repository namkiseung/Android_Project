package com.namkit.namki.examcustomalert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 선택한 아이탬의 번호를 저장한다, 3번에서 사용
    int Choose = 0;

    // 논리값(true, false)의 배열을 담아둔다, 4번에서 사용
    boolean[] MultChoose = {false, false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alert_1(View v){
        // 인플레이션이라는 것을 통해 알림에 레이아웃을 추가하는 예제입니다
        LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_alert1, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("알림");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.setNegativeButton("취소", null);
        // setView를 이용해 레이아웃을 따로 넣을수 있습니다
        // setView(view)에서 view는 위에서 만들었던 View view를 집어넣습니다
        alert.setView(view);
        alert.show();
    }

    public void alert_2(View v){
        new AlertDialog.Builder(this)
                .setTitle("알림")
                // setItems를 이용합니다
                .setItems(R.array.Like,
                        new DialogInterface.OnClickListener(){
                            // 아이탬을 클릭했을경우 아래 메소드가 실행되며 터치된 아이탬 번호는 int which에 담겨 옵니다
                            public void onClick(DialogInterface dialog, int which){
                                // String[]은 문자의 배열을 담습니다 이해하지 않으셔도 됩니다
                                String[] Like = getResources().getStringArray(R.array.Like);
                                // String배열중 고른 아이탬의 순서를 가져와서 Like[which]으로 고른 값을 가져옵니다
                                Toast.makeText(MainActivity.this, "가장 좋아하는것은: " + Like[which], Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }
                        })

                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void alert_3(View v){
        new AlertDialog.Builder(this)
                .setTitle("알림")
                // setSingleChoiceItems을 사용합니다
                .setSingleChoiceItems(R.array.Like, Choose,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                // 고를때마다 선택한 값을 저장합니다
                                Choose=which;
                            }
                        })

                .setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
                        String[] Like = getResources().getStringArray(R.array.Like);
                        Toast.makeText(MainActivity.this, "가장 좋아하는것은: "+Like[Choose], Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소",null)
                .show();
    }

    public void alert_4(View v){
        new AlertDialog.Builder(this)
                .setTitle("알림")
                // setMultiChoiceItems을 사용합니다
                .setMultiChoiceItems(R.array.Like, MultChoose,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                MultChoose[which]=isChecked;
                            }
                        })
                .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String[] foods = getResources().getStringArray(R.array.Like);
                        String string= "가장 좋아하는것은: ";
                        for(int i=0; i<MultChoose.length;i++){
                            if(MultChoose[i]){
                                string += foods[i] + ", ";
                            }
                        }
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("취소",null)
                .show();
    }


}
