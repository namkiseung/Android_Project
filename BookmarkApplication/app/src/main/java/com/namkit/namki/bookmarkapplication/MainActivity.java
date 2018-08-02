package com.namkit.namki.bookmarkapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.namkit.namki.bookmarkapplication.models.Bookmark;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_url; //입력받는 EditText
    private Button btn_add, btn_edit, btn_del; //추가 버튼
    private ListView lv_bookmark; //리스트뷰
    Bookmark bookmark; //GETTER 와 SETTER와 생성자를 포함한 클래스
    int pos=0; //입력된 순서를 세는 변수
    private BookmarkAdapter bookmarkAdapter = new BookmarkAdapter();
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        et_url = (EditText)findViewById(R.id.ed_url);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_del = (Button)findViewById(R.id.btn_del);
        lv_bookmark = (ListView)findViewById(R.id.lv_bookmark);

        lv_bookmark.setAdapter(bookmarkAdapter);
        prefs = getSharedPreferences("setting", MODE_PRIVATE);
        btn_add.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        /*editor = prefs.edit();
        editor.putInt("pos",pos);
        editor.commit();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*this.pos = prefs.getInt("pos", 0);
        for(int i=1; i<=pos; i++) {
            String newgeturl = prefs.getString(String.valueOf(i), ""); //setting이라는 파일을 가져오는데, 해당 파일안에 내용없으면 "" 반환
            bookmark = new Bookmark(newgeturl);//키으로 저장된 횟수 만큼 데이터를 넣으면 된다.
            bookmarkAdapter.addBookmark(bookmark);         //정상 추가 되었을때
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                final String url = et_url.getText().toString();       //editText에 입력한 텍스트를 담는다.
                // if(isUrl(url)){
                bookmark = new Bookmark(url);
                if (bookmarkAdapter.hasDuplicate(bookmark)){     //중복되었을때
                    Toast.makeText(getApplicationContext(), "이미 등록된 주소입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    pos++;
                    Log.d("pos의 값","onClick으로 리스트에 데이터 추가할때 pos값==================="+pos);
                    bookmarkAdapter.addBookmark(bookmark);         //정상 추가 되었을때
                    editor = prefs.edit();  //editor를 사용하기 위해 edit() 함수를 가져온다
                    editor.putString(String.valueOf(pos), url);  // editor 사용준비가 되어서 키,값("url",url)쌍을 넣자. => "1"첫번째 "2" 번째 "3"번째 ....입력될 것이다.
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
                }
                // }else{
                //     showToast(getString(R.string.wrong));
                // }
            break;
            case R.id.btn_edit:
                //delBookmark();
                break;
            case R.id.btn_del:

                break;
        }
    }
    /* public boolean isUrl(String url){
        String urlRegex = "^(file|gopher|news|nntp|telnet|https?|ftps?|sftp)://([a-z0-9-]+.)+[a-z0-9]{2,4}.*$";
        return url.matches(urlRegex);
    }*/

}
