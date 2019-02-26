package com.cameronvoell.articledraftmanager.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PublishedArticleRepository {

    private PublishedArticleDao mPublishedArticleDao;
    private LiveData<List<PublishedArticle>> mAllPublishedArticles;

    public PublishedArticleRepository(Application application) {
        ArticleDraftRoomDatabase db = ArticleDraftRoomDatabase.getDatabase(application);
        mPublishedArticleDao = db.publishedArticleDao();
        mAllPublishedArticles = mPublishedArticleDao.getPublishedArticlesByDateDesc();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PublishedArticle>> getAllArticleDrafts() {
        return mAllPublishedArticles;
    }


    public void insert(PublishedArticle publishedArticle) {
        new insertAsyncTask(mPublishedArticleDao).execute(publishedArticle);
    }
    private static class insertAsyncTask extends AsyncTask<PublishedArticle, Void, Void> {
        private PublishedArticleDao mAsyncTaskDao;
        insertAsyncTask(PublishedArticleDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final PublishedArticle... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    public void delete(PublishedArticle publishedArticle) {
        new deleteAsyncTask(mPublishedArticleDao).execute(publishedArticle);
    }
    private static class deleteAsyncTask extends AsyncTask<PublishedArticle, Void, Void> {
        private PublishedArticleDao mAsyncTaskDao;
        deleteAsyncTask(PublishedArticleDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final PublishedArticle... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

}
