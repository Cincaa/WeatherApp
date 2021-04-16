package com.example.wetherapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RessumeWeekDao {
    @Query("SELECT * FROM resumeWeek")
    List<ResumeWeek> getAll();

    @Query("SELECT * FROM resumeweek where data like :d")
    ResumeWeek getByData(String d);

    @Insert
    void insertAll(ResumeWeek...resumeWeeks);

    @Delete
    void delete(ResumeWeek resumeWeek);
}
