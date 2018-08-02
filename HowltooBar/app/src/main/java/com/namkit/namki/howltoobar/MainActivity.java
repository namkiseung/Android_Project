package com.namkit.namki.howltoobar;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public class MainActivity extends AppCompatActivity {
        private DrawerLayout drawerLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            toolbar.findViewById(R.id.toolbar_email).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "아직 도착한 메세지가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if (item.getItemId() == R.id.first) {
                        getFragmentManager().beginTransaction().replace(R.id.main_framelayout, new FirstFragment()).commit();

                    }
                    if (item.getItemId() == R.id.second) {
                        getFragmentManager().beginTransaction().replace(R.id.main_framelayout, new SecondFragment()).commit();
                    }

                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
            });


        }
    }
}