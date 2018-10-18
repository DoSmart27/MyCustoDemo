package com.vvc.ourcustoapp.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class RoomDateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    @TypeConverter
    public static Calendar fromStringToDate(Long date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            return calendar;
        }
    }

    @TypeConverter
    public static Long fromStringToDate(Calendar date) {
        if (date == null) {
            return null;
        } else {
            date.set(Calendar.HOUR_OF_DAY, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
            return date.getTimeInMillis();
        }
    }
}
