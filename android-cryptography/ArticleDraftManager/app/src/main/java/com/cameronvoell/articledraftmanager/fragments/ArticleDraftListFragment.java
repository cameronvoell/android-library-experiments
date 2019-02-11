package com.cameronvoell.articledraftmanager.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.activities.EditDraftActivity;
import com.cameronvoell.articledraftmanager.adapters.ArticleListAdapter;
import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.viewmodel.ArticleDraftViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleDraftListFragment extends Fragment {

    private ArticleDraftViewModel mArticleDraftViewModel;
    private ArticleListAdapter.OnArticleDraftListInteractionListener mArticleInteractionListener;

    public ArticleDraftListFragment() {
   }

    public static ArticleDraftListFragment newInstance() {
        ArticleDraftListFragment fragment = new ArticleDraftListFragment();
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
        mArticleInteractionListener = new ArticleListAdapter.OnArticleDraftListInteractionListener() {
            @Override
            public void onArticleDraftListInteraction(ArticleDraft item) {
                Intent intent = new Intent();
                intent.putExtra(ArticleDraft.INTENT_EXTRA_NAME, item);
                intent.setClass(getContext(), EditDraftActivity.class);
                startActivity(intent);
            }
        };
        final ArticleListAdapter articleListAdapter = new ArticleListAdapter(getContext(), mArticleInteractionListener);
        recyclerView.setAdapter(articleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mArticleDraftViewModel = ViewModelProviders.of(getActivity()).get(ArticleDraftViewModel.class);

        mArticleDraftViewModel.getAllArticleDrafts().observe(this, new Observer<List<ArticleDraft>>() {
            @Override
            public void onChanged(@Nullable final List<ArticleDraft> articleDrafts) {
                // Update the cached copy of the words in the adapter.
                articleListAdapter.setArticleDrafts(articleDrafts);
            }
        });

        return layout;
    }

}
