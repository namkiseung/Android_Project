package com.namkit.namki.imagelistviewpicasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<Product> arrayList;
ListView lv;
CustomListAdapter adapter;
Context context;
    EditText ed1, ed2, ed;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<Product>();
        lv = (ListView)findViewById(R.id.listView);
         ed = (EditText)findViewById(R.id.imageurl);
         ed1 = (EditText)findViewById(R.id.edit1);
         ed2 = (EditText)findViewById(R.id.edit2);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http//quocnguyen.16mb.com/products.json");
            }
        });
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Product(ed.getText().toString(),ed1.getText().toString(), ed2.getText().toString()
                ));
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray = jsonObject.getJSONArray("products");

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product(
                            productObject.getString("image"),
                            productObject.getString("name"),
                            productObject.getString("price")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), R.layout.custom_list_layout, arrayList);
            lv.setAdapter(adapter);
        }
    }


    private static String readURL(String theUrl){
     StringBuilder content = new StringBuilder();
    try{
     URL url = new URL(theUrl);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
        while ((line = bufferedReader.readLine()) != null){
            content.append(line + "\n");
        }
        bufferedReader.close();
    }catch (Exception e){
        e.printStackTrace();
    }
    return content.toString();
    }
}
