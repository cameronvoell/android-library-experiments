package com.cameronvoell.articledraftmanager.viewmodel;

import android.app.Application;

import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.data.ArticleDraftRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ArticleDraftViewModel extends AndroidViewModel {

    private ArticleDraftRepository mRepository;
    private LiveData<List<ArticleDraft>> mAllArticleDrafts;

    public ArticleDraftViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ArticleDraftRepository(application);
        mAllArticleDrafts = mRepository.getAllArticleDrafts();
    }

    public LiveData<List<ArticleDraft>> getAllArticleDrafts() {
        return mAllArticleDrafts;
    }

    public void insert(ArticleDraft articleDraft) {
        mRepository.insert(articleDraft);
    }

    public void delete(ArticleDraft articleDraft) {
        mRepository.delete(articleDraft);
    }

}