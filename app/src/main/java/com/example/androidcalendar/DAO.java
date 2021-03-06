package com.example.androidcalendar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Query("select * from Entry")
    List<Entry> all();

    @Insert
    void insert(Entry... entries);

    @Delete
    void delete(Entry entry);
}
