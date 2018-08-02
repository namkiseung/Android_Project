package com.namkit.namki.defaultbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button appcom, full, detailitem, itemlist, detailfrag, login, setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appcom = (Button) findViewById(R.id.button7);
        appcom.setOnClickListener(this);
        full = (Button) findViewById(R.id.button6);
        full.setOnClickListener(this);
        detailitem = (Button) findViewById(R.id.button5);
        detailitem.setOnClickListener(this);
        itemlist = (Button) findViewById(R.id.button4);
        itemlist.setOnClickListener(this);
        detailfrag = (Button) findViewById(R.id.button);
        detailfrag.setOnClickListener(this);
        login = (Button) findViewById(R.id.button3);
        login.setOnClickListener(this);
        setting = (Button) findViewById(R.id.button2);
        setting.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*appcom = (Button) findViewById(R.id.button7);
        full = (Button) findViewById(R.id.button6);
        detailitem = (Button) findViewById(R.id.button5);
        itemlist = (Button) findViewById(R.id.button4);
        detailfrag = (Button) findViewById(R.id.button);
        login = (Button) findViewById(R.id.button3);
        setting = (Button) findViewById(R.id.button2);*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button7:
                Intent intent = new Intent(this, AppCompatPreferenceActivity.class);
                startActivity(intent);
                break;
            case R.id.button6:
                Intent intent2 = new Intent(this, FullscreenActivity.class);
                startActivity(intent2);
                break;
            case R.id.button5:
                Intent intent3 = new Intent(this, ItemDetailActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, ItemListActivity.class);
                startActivity(intent4);
                break;
            case R.id.button3:
                Intent intent5 = new Intent(this, LoginActivity.class);
                startActivity(intent5);
                break;
            case R.id.button2:
                Intent intent6 = new Intent(this, SettingsActivity.class);
                startActivity(intent6);
                break;
            case R.id.button:
                Intent inte = new Intent(this, ItemDetailFragment.class);
                startActivity(inte);
                break;
        }
    }

}
