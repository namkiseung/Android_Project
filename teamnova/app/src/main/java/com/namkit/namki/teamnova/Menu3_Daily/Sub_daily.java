package com.namkit.namki.teamnova.Menu3_Daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.namkit.namki.teamnova.R;

public class Sub_daily extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    //public static final int REQUEST_CODE_ANOTHER = 1001;
    TextView sipnnertextview;
    EditText title;
    EditText content;
    Spinner category;
    Button button;
    String titleed, contented, categoryed;
    String items[] = {"카테고리를 선택해주세요.","기타", "개발일지", "피드백", "글쓰기연습", "생각정리"};//스피너에 들어갈 목록;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_daily);
        //*******************************xml파일의 View 아이디 세팅**********************
        sipnnertextview = (TextView)findViewById(R.id.sub_daily_textview);
        title = (EditText) findViewById(R.id.daily_title_edit);
        content = (EditText) findViewById(R.id.daily_content_edit);
        category = (Spinner) findViewById(R.id.spinner_category_edit);
        button = (Button) findViewById(R.id.btn_save);
        //*******************************가져온 Button에 '리스너' 붙히기**********************
        button.setOnClickListener(this);
        //*******************************xml파일의 category 에 '리스너' 붙히고, 어뎁터를 이용해 데이터 세팅**********************
        category.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        category.setAdapter(spinneradapter);
    }

    private void init() {
        titleed = title.getText().toString();
        contented = content.getText().toString();
//        tempsaveData();
    }


    // Button 눌렀을때, 이벤트 호출
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                Intent intent = new Intent(this, Daily.class);   // 다시 돌려줄 인텐트 정보 넣기
                init();
                intent.putExtra("titleed", titleed);   // Edit입력받은 값 인텐트에 저장(제목)
                intent.putExtra("contented", contented); // Edit입력받은 값 인텐트에 저장(내용)
                intent.putExtra("categoryed", categoryed); // Edit입력받은 값 인텐트에 저장(카테고리)
                setResult(RESULT_OK, intent);  //Daily클래스 Intent로 결과값 전달
                finish();  // 해당 액티비티 종료
                break;
        }
    }

    /*private void tempsaveData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);   // 메인 Sharedpreferences 준비
        String memoname = preferences.getString("currentdisplayId", "");   //현재 로그인한 '유저' 값 생성
        SharedPreferences sharedPreferences = getSharedPreferences(memoname + "daily", MODE_PRIVATE); // memoname이 바뀔때마다 새로운 음악이 저장될 것임. 그리고 어차피 모든 계장이 봐야하니까 songname만 다르게.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("titleed", titleed);
        editor.putString("contented", contented);
        editor.putString("categoryed", categoryed);
        editor.apply();
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(items[position] != "카테고리를 선택해주세요.") {
            categoryed = items[position];
        }else {
            items[position] = "";
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        categoryed = "";
    }

    public void onPause() {
        super.onPause();
        init();
        //리스트 해당 아이템 클릭시 값 가져오기
    }
}