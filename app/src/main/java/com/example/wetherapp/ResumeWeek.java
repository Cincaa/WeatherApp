package com.example.wetherapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ResumeWeek {

    @PrimaryKey
    public int id;

    public ResumeWeek(int id, String data, String day, String tempMin, String tempMax) {
        this.id = id;
        this.data = data;
        this.day = day;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String data;

    public String day;

    @ColumnInfo(name="temp_min")
    public String tempMin;

    @ColumnInfo(name="temp_max")
    public String tempMax;
}
