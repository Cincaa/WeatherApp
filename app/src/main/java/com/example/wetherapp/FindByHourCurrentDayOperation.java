package com.example.wetherapp;

import android.os.AsyncTask;

public class FindByHourCurrentDayOperation extends AsyncTask<String,Object,CurrentDay> {
    CurrentDayOperations listener;

    public FindByHourCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }
    @Override
    protected CurrentDay doInBackground(String... hours) {
        CurrentDay cd = null;
        try {
            cd = MyApp.getAppDatabase().currentDayDao().findByHour(hours[0]);
        }catch (Exception e){
            return cd;
        }
        return cd;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(CurrentDay currentDay) {
        listener.findByHourCurrentDay(currentDay);
    }
}
