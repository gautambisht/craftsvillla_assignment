package com.craftvilla.craftsvillatest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private DateUtils() {
    }

    public static String formatDate(String date, String sourceFormat, String targetFormat) {
        try {
            Date d = new SimpleDateFormat(sourceFormat, Locale.getDefault()).parse(date);
            return new SimpleDateFormat(targetFormat, Locale.getDefault()).format(d);
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return null;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date.getTime());
    }

    public static Date formatDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isLeapYear(int year) {
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static long differenceInDays(Date date, Date compareWith) {
        long diff = compareWith.getTime() - date.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
