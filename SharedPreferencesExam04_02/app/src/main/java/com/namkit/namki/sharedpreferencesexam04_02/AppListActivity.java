package com.namkit.namki.sharedpreferencesexam04_02;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class AppListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        ListView listView = findViewById(R.id.list_view);

        PackageManager pm = getPackageManager();//기기에 설치된 모든 앱목록 가져올때 PackageManager 사용. getPackageManager();이거슨 액티비티에서 자동으로 가져올수있음
        List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_META_DATA);//기기에 설치된 모든 앱의 목록을 가져오고, 그 객체가 ApplicationInfo 이거다

        //리스트뷰에 원하는 데이터를 뿌리자
        AppInfoAdapter adapter = new AppInfoAdapter(infos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo info = (ApplicationInfo)parent.getAdapter().getItem(position);
                Intent intent = new Intent();   //데이터를 메인으로 돌려줄때! 일단 클릭한 info가 데이터
                intent.putExtra("info", info);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
