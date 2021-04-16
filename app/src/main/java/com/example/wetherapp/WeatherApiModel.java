package com.example.wetherapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WeatherApiModel {
    private String cnt;
    private List<MomentOfDay> list;

    public WeatherApiModel(String cnt, List<MomentOfDay> list) {
        this.cnt = cnt;
        this.list = list;
    }

    public String getCnt() {
        return cnt;
    }

    public List<MomentOfDay> getList() {
        return list;
    }


}
