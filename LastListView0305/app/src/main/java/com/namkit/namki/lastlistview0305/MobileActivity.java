
package com.namkit.namki.lastlistview0305;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MobileActivity extends AppCompatActivity {

    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        Intent intent = getIntent();
        pos = intent.getExtras().getInt("Position");

        final CustomAdapter adapter = new CustomAdapter(this);
        final TextView name = (TextView)findViewById(R.id.MobilePrice);
       final TextView price = (TextView)findViewById(R.id.price);

        //set data


        name.setText(adapter.names[pos]);
        price.setText(adapter.price[pos]);

    Button btnnext = (Button)findViewById(R.id.Next);
    btnnext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = pos+1;

             name.setText("이름"+adapter.names[position]);

            price.setText("가격"+adapter.price[position]);

            if(!(position>= adapter.getCount()-1)){
                pos +=1;

            }else{
                pos=-1;

            }

        }
    });

    }
}
