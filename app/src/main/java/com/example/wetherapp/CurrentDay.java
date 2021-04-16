package com.example.wetherapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrentDay {
    @PrimaryKey
    public int id;

    public String data;

    public String hour;

    public String temp;

    @ColumnInfo(name="temp_feels")
    public String tempFeels;

    public String humidity;

    public String description;

    @ColumnInfo(name="speed_wind")
    public String speedWind;

    public CurrentDay(int id, String data, String hour, String temp, String tempFeels, String humidity, String description, String speedWind) {
        this.id = id;
        this.data = data;
        this.hour = hour;
        this.temp = temp;
        this.tempFeels = tempFeels;
        this.humidity = humidity;
        this.description = description;
        this.speedWind = speedWind;
    }
}
