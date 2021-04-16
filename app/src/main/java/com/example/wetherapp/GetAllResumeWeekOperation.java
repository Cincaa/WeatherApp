package com.example.wetherapp;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class GetAllResumeWeekOperation extends AsyncTask<Object,Object, List<ResumeWeek>> {
    ResumeWeekOperations listener;

    public GetAllResumeWeekOperation(ResumeWeekOperations listener) {
        this.listener = listener;
    }

    @Override
    protected List<ResumeWeek> doInBackground(Object... objects) {
        List<ResumeWeek> resumeWeekList = new ArrayList<>();
        try {
            resumeWeekList = MyApp.getAppDatabase().ressumeWeekDao().getAll();
        }catch (Exception e)
        {
            return resumeWeekList;
        }
        return resumeWeekList;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<ResumeWeek> resumeWeeks) {
        listener.getAllResumeWeek(resumeWeeks);
    }
}
