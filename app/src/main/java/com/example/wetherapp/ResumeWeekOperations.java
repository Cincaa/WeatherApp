package com.example.wetherapp;

import java.util.List;

public interface ResumeWeekOperations {
    void insertResumeWeek(String result);
    void deleteResumeWeek(String result);
    void getAllResumeWeek(List<ResumeWeek> resumeWeekList);
    void getByDateResumeWeek(ResumeWeek resumeWeek);
    void findByIdResumeWeek(ResumeWeek resumeWeek);
}
