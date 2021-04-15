package com.example.wetherapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class WeatherApiModel implements Parcelable {
    private String cnt;
    private List<MomentOfDay> list;
    private City city;

    public WeatherApiModel(String cnt, List<MomentOfDay> list, City city) {
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }


    public WeatherApiModel() {
    }

    protected WeatherApiModel(Parcel in) {
        cnt = in.readString();
    }

    public static final Creator<WeatherApiModel> CREATOR = new Creator<WeatherApiModel>() {
        @Override
        public WeatherApiModel createFromParcel(Parcel in) {
            return new WeatherApiModel(in);
        }

        @Override
        public WeatherApiModel[] newArray(int size) {
            return new WeatherApiModel[size];
        }
    };

    public String getCnt() {
        return cnt;
    }

    public List<MomentOfDay> getList() {
        return list;
    }

    public City getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cnt);
    }
}
