package com.namkit.namki.a0507progresswork;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    Button state_color, btn_add;
    TextView name, condition, dtime;
    ArrayList<list_item> list_itemArrayList = new ArrayList<list_item>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listView = (ListView)findViewById(R.id.listView);
        MyListAdapter myListAdapter = new MyListAdapter();

        listView.setAdapter(myListAdapter);

        registerForContextMenu(listView);//리스트뷰에 컨텍스트 메뉴 사용하기

        state_color = (Button)findViewById(R.id.state_color);
        btn_add = (Button)findViewById(R.id.btn_add);

        listView.setOnItemClickListener(mItemClickListener);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력값 가져온다
                //보관클래스 만들고 매개변수로 보낸다
                //어댑터객체의 add메서드에  보관객체 넣는다 
            }
        });
        state_color.setOnClickListener(new View.OnClickListener() {//버튼 눌렀을때 내 정보 바꿀때 사용
            @Override
            public void onClick(View view) {
               // state_color.setBackgroundColor(Color.RED);
                Toast.makeText(MainActivity.this, "버튼클릭", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.item, null);
            EditText editTextUsername = (EditText)mView.findViewById(R.id.edit_title);
            EditText editTextPassword = (EditText)mView.findViewById(R.id.edit_desc);
            Button btn_edit = (Button)mView.findViewById(R.id.btn_edit);
            Button btn_del = (Button)mView.findViewById(R.id.btn_del);

            //셋온클릭에 if(공백여부 확인)
            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){  //리스트뷰에서 클릭했을때
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("본인 상태 선택하기");
        menu.add(0, v.getId(), 0, "(초록) 여유");
        menu.add(0, v.getId(), 0, "(노랑) 긴장과 조급");
        menu.add(0, v.getId(), 0, "(빨강) 정신없음");
        menu.add(0, v.getId(), 0, "(검정) 부재중");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="(초록) 여유"){
            Toast.makeText(this, "초록", Toast.LENGTH_SHORT).show();
        }else if(item.getTitle()=="(노랑) 긴장과 조급"){
            Toast.makeText(this, "노랑", Toast.LENGTH_SHORT).show();
        }else if(item.getTitle()=="(빨강) 정신없음"){
            Toast.makeText(this, "빨강", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "검정", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private void init() {
        state_color = (Button) findViewById(R.id.state_color);
        name = (TextView)findViewById(R.id.name);
        condition = (TextView)findViewById(R.id.condition);
        dtime = (TextView)findViewById(R.id.dtime);
    }
    public void saveDate(){

    }
    public void loadData(){

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
     //   saveData();
    }
}
