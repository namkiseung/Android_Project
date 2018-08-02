package com.namkit.namki.gridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    GridView gridView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("뭐야이거", "010-1000-1000", 20, R.drawable.ic_launcher));
        adapter.addItem(new SingerItem("모르겠어", "010-2000-2000", 22, R.drawable.ic_launcher));
        adapter.addItem(new SingerItem("레노버", "010-3000-3000", 21, R.drawable.ic_launcher));
        adapter.addItem(new SingerItem("설명불가", "010-4000-4000", 24, R.drawable.ic_launcher));
        adapter.addItem(new SingerItem("AOA", "010-5000-5000", 25, R.drawable.ic_launcher));

        gridView.setAdapter(adapter);


        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                String mobile = "010-1000-1000";
                int age = 20;

                adapter.addItem(new SingerItem(name, mobile, age, R.drawable.ic_launcher));
                adapter.notifyDataSetChanged();
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "�좏깮 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            int numColumns = gridView.getNumColumns();
            int rowIndex = position / numColumns;
            int columnIndex = position % numColumns;
            Log.d("SingerAdapter", "index : " + rowIndex + ", " + columnIndex);

            return view;
        }
    }

}