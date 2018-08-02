package com.namkit.namki.a0602drawableanimation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
/**
URL : https://www.youtube.com/watch?v=scZYIAZrMWk
유투브 제목 : Drawable Animations - Android Studio Tutorial
*/
public class MainActivity extends AppCompatActivity {
    AnimationDrawable wifiAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        ImageView imageView = (ImageView)findViewById(R.id.image); //현재 뷰에 이미지뷰를 가져온다
        imageView.setBackgroundResource(R.drawable.animation); // 이미지뷰에 리소스 파일을 부여한다
        wifiAnimation=(AnimationDrawable)imageView.getBackground(); //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiAnimation.start();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }
}
