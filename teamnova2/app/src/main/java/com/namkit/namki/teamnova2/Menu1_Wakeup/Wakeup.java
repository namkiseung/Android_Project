package com.namkit.namki.teamnova2.Menu1_Wakeup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.namkit.namki.teamnova2.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Wakeup extends AppCompatActivity {

    ArrayList<WakeupProduct> arrayList;
    ListView lv;
    CustomListAdapter adapter;
    Context context;
    EditText ed1, ed2, ed;
    Button btn_add, btn_album, btn_del;

    private void saveData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String songname = preferences.getString("currentdisplayId"+"wakeup", "");
        SharedPreferences sharedPreferences = getSharedPreferences(songname, MODE_PRIVATE); // songname이 바뀔때마다 새로운 음악이 저장될 것임. 그리고 어차피 모든 계장이 봐야하니까 songname만 다르게.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<WakeupProduct>>() {
        }.getType();//read
        String json = gson.toJson(arrayList, type); // save
        editor.putString("task list3", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String songname = preferences.getString("currentdisplayId"+"wakeup", "");
        SharedPreferences sharedPreferences = getSharedPreferences(songname, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list3", "");
        Type type = new TypeToken<ArrayList<WakeupProduct>>() {
        }.getType();//read
        arrayList = gson.fromJson(json, type);

        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakeup);
        loadData();
        lv = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, R.layout.wakeup_custom_list_layout, arrayList);
        lv.setAdapter(adapter);

        ed = (EditText) findViewById(R.id.imageurl);
        ed1 = (EditText) findViewById(R.id.edit1);
        ed2 = (EditText) findViewById(R.id.edit2);

        final Wakeup wakeup = new Wakeup();
lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.singledeleteList(position);
        saveData();
        return false;
    }
});
        btn_album = (Button) findViewById(R.id.btn_album);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upload = new Intent(getApplicationContext(), WakeupUpload.class);
                startActivityForResult(upload, 10);
            }
        });
        btn_del = (Button) findViewById(R.id.wake_btn_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_del.getText().toString().equals("선택삭제")){
                    btn_del.setText("삭제");
                    adapter.setCheckBox(true);
                    saveData();
                }else{
                    btn_del.setText("삭제");
                    adapter.deleteList();
                    adapter.setCheckBox(false);
                }
            }
        });
        btn_add = (Button) findViewById(R.id.wake_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new WakeupProduct(ed.getText().toString(), ed1.getText().toString(), ed2.getText().toString()
                ));
               // saveData();
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Intent intent = getIntent();
            String resulturi = intent.getStringExtra("image");
            String resulturi2 = intent.getStringExtra("image2");
            ed1.setText(resulturi);
            System.out.println("resulturi 값 : "+resulturi);
            System.out.println("###########################resulturi2 값 : "+resulturi2);
        }
    }
}


