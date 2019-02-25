package com.cameronvoell.articledraftmanager;

import com.cameronvoell.articledraftmanager.utils.DateUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void timeFormatIsCorrect() {
        //mock time is 1549483532780 for 12:05PM February 6, 2019)
        assertEquals("12:05:32 PM - Feb 06, 2019", DateUtils.formatDate(1549483532780l));
    }
}