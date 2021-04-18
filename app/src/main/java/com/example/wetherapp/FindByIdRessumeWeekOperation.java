package com.example.wetherapp;

import android.os.AsyncTask;

public class FindByIdRessumeWeekOperation extends AsyncTask<Integer,Object,ResumeWeek> {

    ResumeWeekOperations listener;

    public FindByIdRessumeWeekOperation(ResumeWeekOperations listener) {
        this.listener = listener;
    }

    @Override
    protected ResumeWeek doInBackground(Integer... integers) {
        ResumeWeek rw = null;
        try {
            rw = MyApp.getAppDatabase().ressumeWeekDao().getById(integers[0]);
        }catch (Exception e)
        {
            return rw;
        }
        return rw;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ResumeWeek resumeWeek) {
        listener.findByIdResumeWeek(resumeWeek);
    }
}
