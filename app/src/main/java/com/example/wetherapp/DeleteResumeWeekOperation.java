package com.example.wetherapp;

import android.os.AsyncTask;

public class DeleteResumeWeekOperation extends AsyncTask<ResumeWeek,Object,Integer> {
    ResumeWeekOperations listener;

    public DeleteResumeWeekOperation(ResumeWeekOperations listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(ResumeWeek... resumeWeeks) {
        Integer id = null;
        try{
            id = resumeWeeks[0].id;
            MyApp.getAppDatabase().ressumeWeekDao().delete(resumeWeeks[0]);
        }catch (Exception e){
            return id;
        }
        return id;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer id) {
        listener.deleteResumeWeek(id);
    }
}
