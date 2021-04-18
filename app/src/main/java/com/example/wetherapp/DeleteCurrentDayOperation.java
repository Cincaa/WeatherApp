package com.example.wetherapp;

import android.os.AsyncTask;

public class DeleteCurrentDayOperation extends AsyncTask<CurrentDay,Object,String> {
    CurrentDayOperations listener;

    public DeleteCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(CurrentDay... CurrentDays) {
        try {
            MyApp.getAppDatabase().currentDayDao().delete(CurrentDays);
        }catch (Exception e)
        {
            return "error";
        }
        return "succes";
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        listener.deleteCurrentDay(result);
    }
}
