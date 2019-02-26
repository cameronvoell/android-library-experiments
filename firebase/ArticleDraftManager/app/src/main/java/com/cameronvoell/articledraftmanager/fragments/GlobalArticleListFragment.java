package com.cameronvoell.articledraftmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.activities.EditDraftActivity;
import com.cameronvoell.articledraftmanager.adapters.ArticleDraftListAdapter;
import com.cameronvoell.articledraftmanager.adapters.PublishedArticleListAdapter;
import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.data.PublishedArticle;
import com.cameronvoell.articledraftmanager.viewmodel.ArticleDraftViewModel;
import com.cameronvoell.articledraftmanager.viewmodel.PublishedArticleViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GlobalArticleListFragment extends Fragment {

    private PublishedArticleViewModel mPublishedArticleViewModel;
    private PublishedArticleListAdapter.OnPublishedArticleListInteractionListener mArticleInteractionListener;

    public GlobalArticleListFragment() {
   }

    public static GlobalArticleListFragment newInstance() {
        GlobalArticleListFragment fragment = new GlobalArticleListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_article_draft_list, container, false);

        RecyclerView recyclerView = layout.findViewById(R.id.articleListRecyclerView);
        mArticleInteractionListener = new PublishedArticleListAdapter.OnPublishedArticleListInteractionListener() {
            @Override
            public void onPublishedArticleListInteraction(PublishedArticle publishedArticle) {
                Intent intent = new Intent();
                intent.putExtra(ArticleDraft.INTENT_EXTRA_NAME, publishedArticle);
                intent.setClass(getContext(), EditDraftActivity.class);
                startActivity(intent);
            }
        };
        final PublishedArticleListAdapter publishedArticleListAdapter = new PublishedArticleListAdapter(getContext(), mArticleInteractionListener);
        recyclerView.setAdapter(publishedArticleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mPublishedArticleViewModel = ViewModelProviders.of(getActivity()).get(PublishedArticleViewModel.class);

        mPublishedArticleViewModel.getAllPublishedArticles().observe(this, new Observer<List<PublishedArticle>>() {
            @Override
            public void onChanged(@Nullable final List<PublishedArticle> publishedArticles) {
                // Update the cached copy of the words in the adapter.
                publishedArticleListAdapter.setPublishedArticltes(publishedArticles);
            }
        });

        return layout;
    }

}
