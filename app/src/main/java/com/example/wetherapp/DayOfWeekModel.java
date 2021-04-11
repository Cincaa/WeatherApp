package com.example.wetherapp;

public class DayOfWeekModel {
    private String day;
    private String minTemp;
    private String maxTemp;

    public DayOfWeekModel(String day, String minTemp, String maxTemp) {
        this.day = day;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getDay() {
        return day;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }


}
