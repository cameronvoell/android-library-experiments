package com.cameronvoell.articledraftmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.fragments.ArticleDraftListFragment;
import com.cameronvoell.articledraftmanager.fragments.GlobalArticleListFragment;
import com.cameronvoell.articledraftmanager.utils.PrefUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;


public class BottomNavigationActivity extends AppCompatActivity {

    private ArticleDraftListFragment mArticleDraftListFragment;
    private GlobalArticleListFragment mGlobalArticleListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_global:
                    navigateToGlobalArticleListFragment();
                    PrefUtils.saveSelectedFragment(getBaseContext(),
                            PrefUtils.SELECTED_FRAGMENT_GLOBAL_ARTICLES);
                    return true;
                case R.id.navigation_drafts:
                    navigateToArticleDraftListFragment();
                    PrefUtils.saveSelectedFragment(getBaseContext(),
                            PrefUtils.SELECTED_FRAGMENT_ARTICLE_DRAFTS);
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

        switch (PrefUtils.getSelectedFragment(this)) {
            case PrefUtils.SELECTED_FRAGMENT_GLOBAL_ARTICLES:
                navigateToGlobalArticleListFragment();
                break;
            case PrefUtils.SELECTED_FRAGMENT_ARTICLE_DRAFTS:
                    navigateToArticleDraftListFragment();
        }
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

    private void navigateToGlobalArticleListFragment (){
        if (mGlobalArticleListFragment == null) {
            mGlobalArticleListFragment = GlobalArticleListFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mGlobalArticleListFragment);
        transaction.commit();
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
