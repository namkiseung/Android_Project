package com.namkit.namki.sharedpreferencesexam04_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1000;
    private ImageView mShrotcut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShrotcut = findViewById(R.id.shortcut_image);

        //처음 로드할때 데이터가 있는지 확인
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String packageName = preferences.getString("shortcut", "입력된 내용이 없습니다");
        if(packageName != null){
            try {
                Drawable icon = getPackageManager().getApplicationIcon(packageName);
                mShrotcut.setImageDrawable(icon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onImageClicked(View view) {
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if(drawable != null){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String packageName = preferences.getString("shortcut", null);

            if(packageName != null){
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                startActivity(intent);
            }
        }
    }

    public void onButtoClicked(View view) {
        Intent intent = new Intent(this, AppListActivity.class);
        startActivityForResult(intent, REQUEST_CODE);//결과 돌려받을때 사용, request code를 1000으로
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){//데이터가 정상으로 넘어오는 필터 역할
            ApplicationInfo info = data.getParcelableExtra("info");
            Drawable icon = info.loadIcon(getPackageManager());

            mShrotcut.setImageDrawable(icon);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("shortcut", info.packageName);
            editor.apply();
        }
    }
}
