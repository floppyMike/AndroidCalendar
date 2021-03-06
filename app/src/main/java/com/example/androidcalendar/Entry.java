package com.example.androidcalendar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Entry {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String text;

    @ColumnInfo
    public Calendar date;

    @ColumnInfo
    public int importance;
}
