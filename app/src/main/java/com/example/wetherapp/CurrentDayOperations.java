package com.example.wetherapp;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface CurrentDayOperations {
    void insertCurrentDay(String result);
    void getAllCurrentDay(List<CurrentDay> currentDayList);
    void deleteCurrentDay(Integer id);
    void findByHourCurrentDay(CurrentDay currentDay);
}
