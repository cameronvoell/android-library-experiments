package com.cameronvoell.articledraftmanager;

import android.content.Context;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cameronvoell.articledraftmanager.activities.EditDraftActivity;
import com.cameronvoell.articledraftmanager.activities.ArticleListActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityTestRule =
            new ActivityTestRule<>(ArticleListActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        assertEquals("com.cameronvoell.articledraftmanager", appContext.getPackageName());
    }

    @Test @LargeTest
    public void fabNavigatesToEditDraft() {
        //Click on fab
        Intents.init();
        onView(withId(R.id.fab)).perform(click());

        //Verify EditDraftActivity is displayed
        intended(hasComponent(EditDraftActivity.class.getName()));
        Intents.release();

    }
}
