package com.example.wetherapp;

import android.os.AsyncTask;

public class FindByIdCurrentDayOperation extends AsyncTask<Integer,Object,CurrentDay> {

    CurrentDayOperations listener;

    public FindByIdCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }

    @Override
    protected CurrentDay doInBackground(Integer... ints) {
        CurrentDay cd = null;
        try{
            cd = MyApp.getAppDatabase().currentDayDao().findById(ints[0]);
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
        listener.findByIdCurrentDay(currentDay);
    }
}
