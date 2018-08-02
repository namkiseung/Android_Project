package com.namkit.namki.examstartactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sub extends AppCompatActivity {
        EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edt = (EditText)findViewById(R.id.edit);
        Button btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sub.this, MainActivity.class);
                intent.putExtra("INPUT_TEXT",edt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
            Button btn_cel = (Button)findViewById(R.id.btn_cancel);
            btn_cel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //setResult((RESULT_CANCELED));
                    finish();
                }
            });
    }
}
