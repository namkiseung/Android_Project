package com.namkit.namki.a0603loadinganimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/**
글제목 : Android Studio - Loading Animation | Tutorial
URL : https://www.youtube.com/watch?v=muEoxGKP3_c
 */
public class MainActivity extends AppCompatActivity {
    AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);


        animation = (AnimationDrawable)imageView.getDrawable();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);
            }
        });
    }

    public void start(View v){
     animation.start();
    }
    public void stop(View v){
        animation.stop();
    }
}
