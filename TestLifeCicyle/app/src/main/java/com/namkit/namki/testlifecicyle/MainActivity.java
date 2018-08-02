package com.namkit.namki.testlifecicyle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnFocusChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLifeCycle = (Button) findViewById(R.id.btn_life_cycle);
        btnLifeCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LifeCycleActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onFocusChange(View view, boolean b) {


}
}
