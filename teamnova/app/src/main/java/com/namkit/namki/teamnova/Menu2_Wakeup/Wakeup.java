package com.namkit.namki.teamnova.Menu2_Wakeup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.ContextMenu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.namkit.namki.teamnova.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Wakeup extends AppCompatActivity implements View.OnClickListener {
    ShareActionProvider provider;
    private static final int WAKEINFO = 2211;
    ArrayList<WakeupProduct> arrayList = new ArrayList<>();
    ListView lv;
    CustomListAdapter adapter;
    Context context;
    Button GONE_btn_del;
    //FloatingActionButton btn_add;
    String mUrl, mName, mTime; //upload해서 가져온 값들을 String에 담자
    FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton btn_add, btn_del, sharedsns;

    private void saveData() {
        SharedPreferences preferences = getSharedPreferences("NOVAMEMBER", MODE_PRIVATE);
        String songname = preferences.getString("currentdisplayId", "");
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
        String songname = preferences.getString("currentdisplayId", "");
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
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.wakeup_floatingActionMenu);
        btn_del = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.wake_btn_del);
        btn_add = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.float_wakeup_add);
        sharedsns = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fwshared);
        GONE_btn_del = (Button)findViewById(R.id.wakeup_basic_btn_del);
        //floatingActionMenu.setOnMenuToggleListener();

        btn_del.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        sharedsns.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, R.layout.wakeup_custom_list_layout, arrayList);
        lv.setAdapter(adapter);

        final Wakeup wakeup = new Wakeup();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                saveData();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        GONE_btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "선택삭제 버튼을 클릭하세요. \n 아니면 삭제할 데이터를 길게 누르세요", Toast.LENGTH_SHORT).show();
                if (GONE_btn_del.getText().toString().equals("선택삭제")) {
                    GONE_btn_del.setText("삭제");
                    adapter.setCheckBox(true);
                } else {
                    GONE_btn_del.setText("선택삭제");
                    adapter.deleteList();
                    adapter.setCheckBox(false);
                }
                saveData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WAKEINFO) {
            if (resultCode == RESULT_OK) {
                data.getStringExtra("image");
                mUrl = data.getStringExtra("wakeurl");
                mName = data.getStringExtra("wakename");
                mTime = data.getStringExtra("wakedate");
                System.out.println("data.getStringExtra(image) 값 :" + data.getStringExtra("image"));
                System.out.println("data.getStringExtra(wakeurl) 값 :" + data.getStringExtra("wakeurl"));
                System.out.println("data.getStringExtra(wakename) 값 :" + data.getStringExtra("wakename"));
                System.out.println("data.getStringExtra(wakedate) 값 :" + data.getStringExtra("wakedate"));
                arrayList.add(new WakeupProduct(mUrl, mName, mTime));
                saveData();
                adapter.notifyDataSetChanged();
           /* Intent intent = getIntent();
            String resulturi = intent.getStringExtra("image");
            String resulturi2 = intent.getStringExtra("image2");*/
                // ed1.setText(resulturi);
                //  System.out.println("resulturi 값 : "+resulturi);
                // System.out.println("###########################resulturi2 값 : "+resulturi2);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_wakeup_add:
                Intent upload = new Intent(getApplicationContext(), WakeupUpload.class);
                startActivityForResult(upload, WAKEINFO);
                break;
            case R.id.wake_btn_del:
                Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                floatingActionMenu.setMenuButtonHideAnimation(fade_in);
                GONE_btn_del.setAnimation(fade_in);
                GONE_btn_del.setVisibility(View.VISIBLE);
                break;
            case R.id.fwshared:
                Intent sns = new Intent(Intent.ACTION_SEND);
                sns.setType("image/*");
                sns.putExtra(Intent.EXTRA_TEXT, mName);
              //sns.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+mUrl));
                startActivity(sns);
                break;
        }
    }
}

/*
* private void sendShare() {
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("image/*");

List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
if (resInfo.isEmpty()) {
return;
}

List<Intent> shareIntentList = new ArrayList<Intent>();

for (ResolveInfo info : resInfo) {
Intent shareIntent = (Intent) intent.clone();

if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
//facebook
shareIntent.setType("text/plain");
shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
// shareIntent.setType("image/jpg");
// shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+mImagePath));
} else {
shareIntent.setType("image/*");
shareIntent.putExtra(Intent.EXTRA_SUBJECT, "제목");
shareIntent.putExtra(Intent.EXTRA_TEXT, "구글 http://www.google.com #");
shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+mImagePath));
}
shareIntent.setPackage(info.activityInfo.packageName);
shareIntentList.add(shareIntent);
}

Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
startActivity(chooserIntent);
}

출처: http://gogorchg.tistory.com/entry/Android-SNS로-데이터-공유하기 [항상 초심으로]
* */


