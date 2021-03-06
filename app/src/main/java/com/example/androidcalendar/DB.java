package com.example.androidcalendar;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = { Entry.class }, version = 1)
@TypeConverters({ Converters.class })
public abstract class DB extends RoomDatabase {
    public abstract DAO dao();
}
