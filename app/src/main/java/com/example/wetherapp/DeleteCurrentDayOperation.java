package com.example.wetherapp;

import android.os.AsyncTask;

public class DeleteCurrentDayOperation extends AsyncTask<CurrentDay,Object,Integer> {
    CurrentDayOperations listener;

    public DeleteCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(CurrentDay... currentDays) {
        Integer id = null;
        try {
            id = currentDays[0].id;
            MyApp.getAppDatabase().currentDayDao().delete(currentDays[0]);
        }catch (Exception e)
        {
            return id;
        }
        return id;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer id) {
        listener.deleteCurrentDay(id);
    }
}
