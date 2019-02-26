package com.cameronvoell.articledraftmanager.viewmodel;

import android.app.Application;

import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.data.ArticleDraftRepository;
import com.cameronvoell.articledraftmanager.data.PublishedArticle;
import com.cameronvoell.articledraftmanager.data.PublishedArticleRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PublishedArticleViewModel extends AndroidViewModel {

    private PublishedArticleRepository mRepository;
    private LiveData<List<PublishedArticle>> mAllPublishedArticles;

    public PublishedArticleViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PublishedArticleRepository(application);
        mAllPublishedArticles = mRepository.getAllArticleDrafts();
    }

    public LiveData<List<PublishedArticle>> getAllPublishedArticles() {
        return mAllPublishedArticles;
    }

    public void insert(PublishedArticle publishedArticle) {
        mRepository.insert(publishedArticle);
    }

    public void delete(PublishedArticle publishedArticle) {
        mRepository.delete(publishedArticle);
    }

}