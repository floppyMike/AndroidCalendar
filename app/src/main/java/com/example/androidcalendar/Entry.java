package com.example.androidcalendar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Entry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Entry() {
    }

    public Entry(String t, Calendar d, int imp) {
        text = t;
        date = d;
        importance = imp;
    }

    @ColumnInfo
    public String text;

    @ColumnInfo
    public Calendar date;

    @ColumnInfo
    public int importance;
}
