package com.example.wetherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomePage()).commit();
        volleyGet();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomePage();
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
        startActivity(intent);

    }

    public void volleyGet(){
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901&appid=d958fa2856e3c17c0eedcec1edc1561a";
        List<String> jsonResponses = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("list");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = jsonObject.getJSONArray("weather");
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                        String weatherCondition = jsonObject1.getString("main");

//                      jsonResponses.add();
                        Log.d("debug",weatherCondition);
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

    }
}