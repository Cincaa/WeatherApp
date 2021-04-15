package com.example.wetherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //https://api.openweathermap.org/data/2.5/forecast?q=Bucharest&units=metric&appid=d958fa2856e3c17c0eedcec1edc1561a

    @GET("data/2.5/forecast")
    Call<WeatherApiModel> getWeather(
            @Query("q") String city,
            @Query("appid") String appid
    );
}
