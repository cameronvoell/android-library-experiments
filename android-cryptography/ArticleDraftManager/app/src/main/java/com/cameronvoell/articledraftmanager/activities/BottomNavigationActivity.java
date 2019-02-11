package com.cameronvoell.articledraftmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.fragments.ArticleDraftListFragment;
import com.cameronvoell.articledraftmanager.utils.PrefUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


public class BottomNavigationActivity extends AppCompatActivity {

    private ArticleDraftListFragment mArticleDraftListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigateToArticleDraftListFragment();
                    PrefUtils.saveSelectedFragment(getBaseContext(), PrefUtils.SELECTED_FRAGMENT_1);
                    Toast.makeText(getApplicationContext(),
                            "selected article list",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    navigateToArticleDraftListFragment();
                    Toast.makeText(getApplicationContext(),
                            "selected fragment 2",
                            Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    navigateToArticleDraftListFragment();
                    Toast.makeText(getApplicationContext(),
                            "selected fragment 3",
                            Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigateToArticleDraftListFragment();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), EditDraftActivity.class);
                startActivity(intent);
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

    private void navigateToArticleDraftListFragment (){
        if (mArticleDraftListFragment == null) {
            mArticleDraftListFragment = ArticleDraftListFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mArticleDraftListFragment);
        transaction.commit();
    }
}
