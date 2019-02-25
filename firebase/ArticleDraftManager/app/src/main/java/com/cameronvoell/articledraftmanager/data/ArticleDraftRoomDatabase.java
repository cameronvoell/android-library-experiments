package com.cameronvoell.articledraftmanager.data;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities={ArticleDraft.class}, version=3)
public abstract class ArticleDraftRoomDatabase extends RoomDatabase {

    public abstract ArticleDraftDao articleDraftDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile ArticleDraftRoomDatabase INSTANCE;

    static ArticleDraftRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArticleDraftRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ArticleDraftRoomDatabase.class, "article_draft_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            //new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ArticleDraftDao mDao;

        PopulateDbAsync(ArticleDraftRoomDatabase db) {
            mDao = db.articleDraftDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

//            ArticleDraft word = new ArticleDraft("Hello", "hello world", "sept 9, 1989");
//            mDao.insert(word);
//            word = new ArticleDraft("World", "who dat?", "sept 11, 1988");
//            mDao.insert(word);
            return null;
        }
    }

}
