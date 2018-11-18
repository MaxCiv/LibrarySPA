package com.maxciv.library.util;

import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class LibraryUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Nullable
    public static String getStringFromFormattedDate(@Nullable Date date) {
        if (date == null) return null;
        return DATE_FORMAT.format(date);
    }

    @Nullable
    public static String getStringTimeFromFormattedDate(@Nullable Date date) {
        if (date == null) return null;
        return TIME_FORMAT.format(date);
    }

    @Nullable
    public static Date getDateFromFormattedString(@Nullable String dateString) {
        if (dateString == null) return null;
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
