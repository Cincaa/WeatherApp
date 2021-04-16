package com.example.wetherapp;

import android.os.AsyncTask;

import java.nio.channels.AsynchronousChannelGroup;

public class GetByDateResumeWeek extends AsyncTask<String,Object,ResumeWeek> {
    ResumeWeekOperations listener;

    public GetByDateResumeWeek(ResumeWeekOperations listener) {
        this.listener = listener;
    }

    @Override
    protected ResumeWeek doInBackground(String... strings) {
        ResumeWeek resumeWeek = null;
        try {
            resumeWeek = MyApp.getAppDatabase().ressumeWeekDao().getByData(strings[0]);
        }catch (Exception e){
            return resumeWeek;
        }
        return resumeWeek;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ResumeWeek resumeWeek) {
        listener.getByDateResumeWeek(resumeWeek);
    }
}
