package com.example.androidcalendar;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Converters {
    @TypeConverter
    public static Calendar fromUnixTime(Long v) {
        return v == null ? null : new Calendar.Builder().setInstant(v).build();
    }

    @TypeConverter
    public static Long fromCalendar(Calendar c) {
        return c == null ? null : c.getTime().getTime();
    }
}
