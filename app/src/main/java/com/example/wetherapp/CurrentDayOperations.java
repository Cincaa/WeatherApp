package com.example.wetherapp;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface CurrentDayOperations {
    void insertCurrentDay(String result);
    void getAllCurrentDay(List<CurrentDay> currentDayList);
    void deleteCurrentDay(String result);
    void findByHourCurrentDay(CurrentDay currentDay);
    void findByIdCurrentDay(CurrentDay currentDay);
}
