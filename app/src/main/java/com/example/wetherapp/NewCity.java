package com.example.wetherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewCity extends Fragment {

    public EditText newCity;
    public TextView temp;
    public TextView descrip;
    public ImageView img;
    public Button button;
    public NewCity() {
        super(R.layout.new_city);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        newCity = v.findViewById(R.id.textCity);
        temp = v.findViewById(R.id.tempNewCity);
        descrip = v.findViewById(R.id.weatherCondition);
        img = v.findViewById(R.id.imageNewCity);

        button = v.findViewById(R.id.newCityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newCity.getText() != null){
                    String city = newCity.getText().toString();
                    Call<WeatherApiModel> call = ApiBuilder.getInstance().getWeatherByCity(
                            city,ApiBuilder.UNITS,ApiBuilder.APP_ID
                    );
                    call.enqueue(new Callback<WeatherApiModel>() {
                        @Override
                        public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                            if (response.body() != null) {
                                MomentOfDay md = response.body().getList().get(0);
                                temp.setText(md.getMain().getTemp());
                                String desc = md.getWeather().get(0).getMain();
                                img.setVisibility(v.VISIBLE);
                                switch (desc) {
                                    case "Clouds":
                                        img.setImageDrawable(getResources().getDrawable(R.drawable.clouds, getActivity().getApplicationContext().getTheme()));
                                        descrip.setText("The temperature is " + md.getMain().getTemp() +
                                                " centigrade, but it feels like " +  md.getMain().getFeels_like() + ".\n" +
                                                "Partly cloudy right now. There is a chance of some rain too" +
                                                ", so don’t leave home without your umbrella!\n"+
                                                "Wind speed: " + md.getWind().getSpeed() + " km/h.");
                                        break;
                                    case "Rain":
                                        img.setImageDrawable(getResources().getDrawable(R.drawable.rain, getActivity().getApplicationContext().getTheme()));
                                        descrip.setText("The temperature is " + md.getMain().getTemp()+
                                                " centigrade, but it feels like " +  md.getMain().getFeels_like() + ".\n" +
                                                "Considerable cloudiness with occasional rain showers.\n"+
                                                "Wind speed: " + md.getWind().getSpeed() + " km/h.");
                                        break;
                                    case "Snow":
                                        img.setImageDrawable(getResources().getDrawable(R.drawable.snow, getActivity().getApplicationContext().getTheme()));
                                        descrip.setText("The temperature is " + md.getMain().getTemp() +
                                                " centigrade, but it feels like " +  md.getMain().getFeels_like() + ".\n" +
                                                "Variably cloudy with snow showers. Chance of snow accumulations.\n"+
                                                "Wind speed: " + md.getWind().getSpeed() + " km/h.");
                                        break;
                                    case "Clear":
                                        img.setImageDrawable(getResources().getDrawable(R.drawable.sun, getActivity().getApplicationContext().getTheme()));
                                        descrip.setText("The temperature is " + md.getMain().getTemp() +
                                                " centigrade, but it feels like " + md.getMain().getFeels_like() + ".\n" +
                                                "Mainly sunny. A mostly clear sky.\n" +
                                                "Wind speed: " + md.getWind().getSpeed() + " km/h.");
                                        break;
                                }
                            }else {
                                temp.setText("");
                                descrip.setText("Can't find this city");
                                img.setVisibility(v.INVISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherApiModel> call, Throwable t) {

                        }
                    });
                }

            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
