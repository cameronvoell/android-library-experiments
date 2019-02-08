package com.cameronvoell.articledraftmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.adapters.ArticleListAdapter;
import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.viewmodel.ArticleDraftViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ArticleListActivity extends AppCompatActivity {

    private ArticleDraftViewModel mArticleDraftViewModel;
    private ArticleListAdapter.OnArticleDraftListInteractionListener mArticleInteractionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.articleListRecyclerView);
        mArticleInteractionListener = new ArticleListAdapter.OnArticleDraftListInteractionListener() {
            @Override
            public void onArticleDraftListInteraction(ArticleDraft item) {
                Intent intent = new Intent();
                intent.putExtra(ArticleDraft.INTENT_EXTRA_NAME, item);
                intent.setClass(getApplicationContext(), EditDraftActivity.class);
                startActivity(intent);
            }
        };
        final ArticleListAdapter articleListAdapter = new ArticleListAdapter(this, mArticleInteractionListener);
        recyclerView.setAdapter(articleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mArticleDraftViewModel = ViewModelProviders.of(this).get(ArticleDraftViewModel.class);

        mArticleDraftViewModel.getAllArticleDrafts().observe(this, new Observer<List<ArticleDraft>>() {
            @Override
            public void onChanged(@Nullable final List<ArticleDraft> articleDrafts) {
                // Update the cached copy of the words in the adapter.
                articleListAdapter.setArticleDrafts(articleDrafts);
            }
        });

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
}
