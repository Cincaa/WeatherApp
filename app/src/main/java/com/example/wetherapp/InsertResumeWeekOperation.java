package com.example.wetherapp;

import android.os.AsyncTask;

public class InsertResumeWeekOperation extends AsyncTask<ResumeWeek,Object,String> {
    ResumeWeekOperations listener;

    public InsertResumeWeekOperation(ResumeWeekOperations listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(ResumeWeek... resumeWeeks){
        try {
            MyApp.getAppDatabase().ressumeWeekDao().insertAll(resumeWeeks);
        }catch (Exception e){
            return "Error insert resumeWeek";
        }
        return "Accept insert resumeWeek";
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        listener.insertResumeWeek(result);
    }


}
