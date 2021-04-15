package com.example.wetherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.LoginStatusCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class MainActivity extends AppCompatActivity {

    public Bundle homePage_bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //volleyGet();
        Call<WeatherApiModel> call = ApiBuilder.getInstance().getWeather(
                    "Bucharest",ApiBuilder.APP_ID
                );

        call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                homePage_bundle.putString("city",response.body().getCity().getName());
                BottomNavigationView bottomNav = findViewById(R.id.bottom_navbar);
                bottomNav.setOnNavigationItemSelectedListener(navListener);
                Fragment frg = new HomePage();
                frg.setArguments(homePage_bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frg).commit();
            }

            @Override
            public void onFailure(Call<WeatherApiModel> call, Throwable t) {

            }
        });




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomePage();
                            selectedFragment.setArguments(homePage_bundle);
                            break;
                        case R.id.nav_saved_cities:
                            selectedFragment = new SavedCitiesActivity();
                            break;
                        case R.id.nav_log_out:
                            gotoLogIn();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };

    private void gotoLogIn(){
//       TODO: Custom logOut button doesn't work, fix that
        
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this,LogInActivity.class);
        intent.putExtra("LOG_OUT", true);
        startActivity(intent);


    }

    /*public void volleyGet(){
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=Bucharest&appid=d958fa2856e3c17c0eedcec1edc1561a";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    String city = response.getJSONObject("city").getString("name");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String data = jsonObject.getString("dt_txt");
                        Double temp = jsonObject.getJSONObject("main").getDouble("temp");
                        Double minTemp = jsonObject.getJSONObject("main").getDouble("temp_min");
                        Double maxTemp = jsonObject.getJSONObject("main").getDouble("temp_max");
                        String weatherCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
                        weatherApi.add(new WeatherApiModel(city,data,temp,weatherCondition,minTemp,maxTemp));
                    }
                } catch (JSONException e) {
                    Log.d("debug","1");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug","2");
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }*/
}