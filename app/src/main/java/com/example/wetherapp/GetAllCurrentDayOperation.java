package com.example.wetherapp;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class GetAllCurrentDayOperation extends AsyncTask<Object,Object,List<CurrentDay>> {

    CurrentDayOperations listener;

    public GetAllCurrentDayOperation(CurrentDayOperations listener) {
        this.listener = listener;
    }

    @Override
    protected List<CurrentDay> doInBackground(Object[] objects) {
        List<CurrentDay> currentDayList = new ArrayList<>();
        try {
            currentDayList = MyApp.getAppDatabase().currentDayDao().getAll();
        }catch (Exception e){
            return currentDayList;
        }
        return currentDayList;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<CurrentDay> result) {
        listener.getAllCurrentDay(result);
    }
}
