package com.example.wetherapp;

import java.util.List;

public interface ResumeWeekOperations {
    void insertResumeWeek(String result);
    void deleteResumeWeek(Integer id);
    void getAllResumeWeek(List<ResumeWeek> resumeWeekList);
    void getByDateResumeWeek(ResumeWeek resumeWeek);
}
