package com.cameronvoell.articledraftmanager.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ArticleDraftRepository {

    private ArticleDraftDao mArticleDraftDao;
    private LiveData<List<ArticleDraft>> mAllArticleDrafts;

    public ArticleDraftRepository(Application application) {
        ArticleDraftRoomDatabase db = ArticleDraftRoomDatabase.getDatabase(application);
        mArticleDraftDao = db.articleDraftDao();
        mAllArticleDrafts = mArticleDraftDao.getArticleDraftsByDateDesc();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ArticleDraft>> getAllArticleDrafts() {
        return mAllArticleDrafts;
    }


    public void insert(ArticleDraft articleDraft) {
        new insertAsyncTask(mArticleDraftDao).execute(articleDraft);
    }
    private static class insertAsyncTask extends AsyncTask<ArticleDraft, Void, Void> {
        private ArticleDraftDao mAsyncTaskDao;
        insertAsyncTask(ArticleDraftDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final ArticleDraft... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public void delete(ArticleDraft articleDraft) {
        new deleteAsyncTask(mArticleDraftDao).execute(articleDraft);
    }
    private static class deleteAsyncTask extends AsyncTask<ArticleDraft, Void, Void> {
        private ArticleDraftDao mAsyncTaskDao;
        deleteAsyncTask(ArticleDraftDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final ArticleDraft... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

}
