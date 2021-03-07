package com.example.androidcalendar;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Entry.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DB extends RoomDatabase {
    public abstract DAO dao();

    private static DB db;

    public static DB getInstance() {
        assert db != null;
        return db;
    }

    public static void build(Context c) {
        db = Room.databaseBuilder(c, DB.class, "Dates").allowMainThreadQueries().build();
    }
}
