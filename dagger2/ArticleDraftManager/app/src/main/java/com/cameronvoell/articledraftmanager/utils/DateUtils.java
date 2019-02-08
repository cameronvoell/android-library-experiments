package com.cameronvoell.articledraftmanager.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public  class DateUtils {

    public static String formatDate(long timeMillis){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a - MMM dd, yyyy");
        String dateString = format.format(cal.getTime());
        return dateString;
    }
}
