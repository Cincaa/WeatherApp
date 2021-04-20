package com.example.wetherapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrentDayDao {
    @Query("SELECT * FROM CurrentDay")
    List<CurrentDay> getAll();

    @Query("SELECT * FROM CurrentDay WHERE hour LIKE :h")
    CurrentDay findByHour(String h);

    @Insert
    void insertAll(CurrentDay...currentDays);

    @Delete
    void delete(CurrentDay currentDay);
}

