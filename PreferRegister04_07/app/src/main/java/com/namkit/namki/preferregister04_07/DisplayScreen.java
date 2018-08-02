package com.namkit.namki.preferregister04_07;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by namki on 2018-03-13.
 */

public class DisplayScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String display = preferences.getString("display","");

        TextView displayInfo = (TextView)findViewById(R.id.textViewName);
        displayInfo.setText(display);
    }
}

