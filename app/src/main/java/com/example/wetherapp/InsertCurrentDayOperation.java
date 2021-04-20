package com.example.wetherapp;

import android.os.AsyncTask;


public class InsertCurrentDayOperation extends AsyncTask<CurrentDay,Object,String> {

    CurrentDayOperations listener;

    public InsertCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(CurrentDay... currentDays) {
        try {
            MyApp.getAppDatabase().currentDayDao().insertAll(currentDays);
        }catch (Exception e)
        {
            return "Error insert currentDay";
        }
        return "Succes insert currentDay";
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        listener.insertCurrentDay(result);
    }
}
