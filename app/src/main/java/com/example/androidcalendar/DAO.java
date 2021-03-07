package com.example.androidcalendar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Calendar;
import java.util.List;

@Dao
public interface DAO {
    @Query("select * from Entry")
    List<Entry> all();

    @Query("select * from Entry where Entry.date >= :cal")
    List<Entry> allFuture(Calendar cal);

    @Query("select * from Entry where Entry.date < :cal")
    List<Entry> allPast(Calendar cal);

    @Insert
    void insert(Entry... entries);

    @Delete
    void delete(Entry entry);
}
