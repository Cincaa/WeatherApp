package com.example.wetherapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CurrentDay.class,ResumeWeek.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CurrentDayDao currentDayDao();
    public abstract RessumeWeekDao ressumeWeekDao();
}
