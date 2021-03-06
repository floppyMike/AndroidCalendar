package com.example.androidcalendar;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Entry.class }, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract DAO dao();
}
