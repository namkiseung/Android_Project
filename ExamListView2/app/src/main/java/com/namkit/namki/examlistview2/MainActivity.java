package com.namkit.namki.examlistview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.namkit.namki.examlistview2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    TextView text1;
    ListView list1;
    ArrayList<String> array;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        for(int ii=0; ii<20; ii++) {
            array.add("리스트맨" + ii);
        }
        text1 = (TextView)findViewById(R.id.textView1);
        list1 = (ListView)findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, array);
        list1.setAdapter(adapter);

        ListListener listListener = new ListListener();
        list1.setOnItemClickListener(listListener);
    }
    class ListListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String str = array.get(i);
            text1.setText(str);
        }
    }
}
