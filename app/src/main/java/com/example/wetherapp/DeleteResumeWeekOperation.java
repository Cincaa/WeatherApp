package com.example.wetherapp;

import android.os.AsyncTask;

public class DeleteResumeWeekOperation extends AsyncTask<ResumeWeek,Object,String> {
    ResumeWeekOperations listener;

    public DeleteResumeWeekOperation(ResumeWeekOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(ResumeWeek... resumeWeeks) {
        try{
            MyApp.getAppDatabase().ressumeWeekDao().delete(resumeWeeks[0]);
        }catch (Exception e){
            return "error";
        }
        return "accept";
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        listener.deleteResumeWeek(s);
    }
}
