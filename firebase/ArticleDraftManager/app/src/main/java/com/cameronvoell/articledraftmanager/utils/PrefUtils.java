package com.cameronvoell.articledraftmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    private final static String SHARED_PREFERENCES = "article_draft_manager_preferences";
    private final static int PREF_MODE = android.content.Context.MODE_PRIVATE;

    private final static String PREF_SELECTED_FRAGMENT = "pref_selected_fragment";
    public final static int SELECTED_FRAGMENT_GLOBAL_ARTICLES = 0;
    public final static int SELECTED_FRAGMENT_ARTICLE_DRAFTS = 1;

    private static SharedPreferences sp(Context baseContext) {
        return baseContext.getSharedPreferences(SHARED_PREFERENCES, PREF_MODE);
    }

    public static int getSelectedFragment(Context ctx) {
        return sp(ctx).getInt(PREF_SELECTED_FRAGMENT, SELECTED_FRAGMENT_GLOBAL_ARTICLES);
    }

    public static void saveSelectedFragment(Context ctx, int whichFragment) {
        sp(ctx).edit().putInt(PREF_SELECTED_FRAGMENT, whichFragment).commit();
    }
}
