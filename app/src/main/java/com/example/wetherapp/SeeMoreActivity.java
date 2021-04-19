package com.example.wetherapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeeMoreActivity extends Fragment implements CurrentDayOperations {

    public TextView temperature;
    public TextView city;
    public TextView weatherCondition;
    public ImageView img;

    public SeeMoreActivity() {
        super(R.layout.see_more);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);
        temperature = v.findViewById(R.id.temperature);
        img = v.findViewById(R.id.imageView);
        //city = v.findViewById(R.id.cityName);
        weatherCondition = v.findViewById(R.id.weatherCondition);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH");
        String currentHour = dateFormat.format(date);
        String hour = String.valueOf(3 * (Integer.valueOf(currentHour) / 3));
        new FindByHourCurrentDayOperation(this).execute(hour);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city = view.findViewById(R.id.cityName);
        Bundle bundle = getArguments();
        city.setText(bundle.getString("city"));
    }

    @Override
    public void insertCurrentDay(String result) {

    }

    @Override
    public void getAllCurrentDay(List<CurrentDay> currentDayList) {

    }

    @Override
    public void deleteCurrentDay(Integer result) {

    }

    @Override
    public void findByHourCurrentDay(CurrentDay currentDay) {
        if (currentDay != null) {
            //TODO: de inlocuit si aici orasul dat de locatie
            temperature.setText(currentDay.temp + "°C");
            String desc = currentDay.description;

            switch (desc) {
                case "Clouds":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.clouds, getActivity().getApplicationContext().getTheme()));
                    weatherCondition.setText("The temperature is " + currentDay.temp +
                            " centigrade, but it feels like " + currentDay.tempFeels + ".\n" +
                            "Partly cloudy right now. There is a chance of some rain too" +
                            ", so don’t leave home without your umbrella!\n"+
                            "Wind speed: " + currentDay.speedWind + " km/h.");
                    break;
                case "Rain":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.rain, getActivity().getApplicationContext().getTheme()));
                    weatherCondition.setText("The temperature is " + currentDay.temp +
                            " centigrade, but it feels like " + currentDay.tempFeels + ".\n" +
                            "Considerable cloudiness with occasional rain showers.\n"+
                            "Wind speed: " + currentDay.speedWind + " km/h.");
                    break;
                case "Snow":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.snow, getActivity().getApplicationContext().getTheme()));
                    weatherCondition.setText("The temperature is " + currentDay.temp +
                            " centigrade, but it feels like " + currentDay.tempFeels + ".\n" +
                            "Variably cloudy with snow showers. Chance of snow accumulations.\n"+
                            "Wind speed: " + currentDay.speedWind + " km/h.");
                    break;
                case "Clear":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.sun, getActivity().getApplicationContext().getTheme()));
                    weatherCondition.setText("The temperature is " + currentDay.temp +
                            " centigrade, but it feels like " + currentDay.tempFeels + ".\n" +
                            "Mainly sunny. A mostly clear sky.\n" +
                            "Wind speed: " + currentDay.speedWind + " km/h.");
                    break;
            }
        }

    }

    @Override
    public void findByIdCurrentDay(CurrentDay currentDay) {

    }
}
