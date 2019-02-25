package com.cameronvoell.articledraftmanager.data;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArticleDraftDao {
    @Query("SELECT * from article_draft_table ORDER BY date DESC")
    LiveData<List<ArticleDraft>> getArticleDraftsByDateDesc();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleDraft articleDraft);

    @Delete
    void delete(ArticleDraft articleDraft);

    @Query("DELETE FROM article_draft_table")
    void deleteAll();
}
