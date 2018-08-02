package com.namkit.namki.examlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class New_text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text);

       /* Button addItem = (Button)findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence t = addText.getText();
                if(t.size() > 0)
                    list.add(t.toString());
                else list.add("Item");
                adapter.notifyDataSetChange();
            }
        });*/
    }
}
