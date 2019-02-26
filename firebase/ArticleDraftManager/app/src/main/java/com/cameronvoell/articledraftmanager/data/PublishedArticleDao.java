package com.cameronvoell.articledraftmanager.data;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PublishedArticleDao {
    @Query("SELECT * from published_article_table ORDER BY date DESC")
    LiveData<List<PublishedArticle>> getPublishedArticlesByDateDesc();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PublishedArticle publishedArticle);

    @Delete
    void delete(PublishedArticle publishedArticle);

    @Query("DELETE FROM published_article_table")
    void deleteAll();
}
