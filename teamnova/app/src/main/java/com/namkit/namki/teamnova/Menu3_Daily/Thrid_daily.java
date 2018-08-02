package com.namkit.namki.teamnova.Menu3_Daily;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.namkit.namki.teamnova.R;

import java.util.ArrayList;

public class Thrid_daily extends AppCompatActivity {
Button mlistview;
TextView mtitle, mcontent, mcate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid_daily);

        init();



        /*arraylist = new ArrayList<Daily_MainItem>();
        arraylist = getIntent().getParcelableArrayListExtra("key");*/



       /* String a = intent.getStringExtra("title");
        String b =intent.getStringExtra("desc");
        String c =intent.getStringExtra("cate");*/


        //ArrayList<Daily_MainItem> a = (ArrayList<Daily_MainItem>) getIntent().getSerializableExtra("data");



    }
    private void init() {
        mlistview = (Button)findViewById(R.id.btn_viewlist);
        mtitle = (TextView)findViewById(R.id.daily_title_view);
        mcontent = (TextView)findViewById(R.id.daily_content_view);
        mcate = (TextView)findViewById(R.id.daily_cate_view);
        mlistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Daily.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Daily_MainItem r =intent.getParcelableExtra("key");
        mtitle.setText("[제목] : "+r.getTitle());
        mcate.setText("[분류] : "+r.getCategory());
        mcontent.setText("[내용] : "+r.getDescription());
        //mtitle.setText(arraylist.ti);
    }
}
