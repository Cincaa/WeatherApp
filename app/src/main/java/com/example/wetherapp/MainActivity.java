package com.example.wetherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class MainActivity extends AppCompatActivity implements CurrentDayOperations,ResumeWeekOperations{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<WeatherApiModel> call = ApiBuilder.getInstance().getWeather(
                "Bucharest",ApiBuilder.UNITS,ApiBuilder.APP_ID
        );

        call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, retrofit2.Response<WeatherApiModel> response) {
                List<MomentOfDay> weather = response.body().getList();
                for(int i = 0; i < 8; i ++ ){
                    new FindByIdCurrentDayOperation(MainActivity.this).execute(0);
                }

                //new GetAllCurrentDayOperation(MainActivity.this).execute(new Object());
                //new GetAllResumeWeekOperation(MainActivity.this).execute(new Object());
                String date = takeDate();
                int idCurentDay = 0;
                for (MomentOfDay momentOfDay : weather)
                {
                    String dt = momentOfDay.getDt_txt();
                    if(dt.contains(date))
                    {
                        String[] split = dt.split(" ");
                        String hour = split[1].substring(0,2);
                        String data = split[0];
                        String temp = momentOfDay.getMain().getTemp();
                        String tempFeels = momentOfDay.getMain().getFeels_like();
                        String humidity = momentOfDay.getMain().getHumidity();
                        String descripton = momentOfDay.getWeather().get(0).getMain();
                        String speedWind = momentOfDay.getWind().getSpeed();
                        CurrentDay currentDay = new CurrentDay(idCurentDay,data,hour,temp,tempFeels,humidity,descripton,speedWind);
                        new InsertCurrentDayOperation(MainActivity.this).execute(currentDay);
                        idCurentDay = idCurentDay + 1;
                    }
                    else {
                        break;
                    }
                }
                List<String> days = new ArrayList<>();
                days.add("Sunday");
                days.add("Monday");
                days.add("Tuesday");
                days.add("Wednesday");
                days.add("Thursday");
                days.add("Friday");
                days.add("Saturday");
                int nrDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                String day = "Today";
                Double tempMin = Double.valueOf(weather.get(0).getMain().getTemp_min());
                Double tempMax = Double.valueOf(weather.get(0).getMain().getTemp_max());
                String dt = weather.get(0).getDt_txt();
                String[] split = dt.split(" ");
                date = split[0];
                int id = 0;
                for(MomentOfDay momentOfDay : weather){
                    dt = momentOfDay.getDt_txt();
                    if (dt.contains(date)){
                        Double tempMaxAux = Double.valueOf(momentOfDay.getMain().getTemp_max());
                        Double tempMinAux = Double.valueOf(momentOfDay.getMain().getTemp_min());
                        if (tempMaxAux > tempMax)
                        {
                            tempMax = tempMaxAux;
                        }
                        if (tempMinAux < tempMin){
                            tempMin = tempMinAux;
                        }
                    }
                    else{
                        ResumeWeek rw = new ResumeWeek(id,date,day,tempMin.toString(),tempMax.toString());
                        new InsertResumeWeekOperation(MainActivity.this).execute(rw);
                        id = id + 1;
                        nrDay = nrDay + 1;
                        if (nrDay == 8){
                            nrDay = 1;
                        }
                        day = days.get(nrDay-1);
                        tempMax = Double.valueOf(momentOfDay.getMain().getTemp_max());
                        tempMin = Double.valueOf(momentOfDay.getMain().getTemp_min());
                        split = dt.split(" ");
                        date = split[0];
                    }

                }
                BottomNavigationView bottomNav = findViewById(R.id.bottom_navbar);
                bottomNav.setOnNavigationItemSelectedListener(navListener);
                Fragment frg = new HomePage();
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
                            break;
                        case R.id.nav_saved_cities:
                            selectedFragment = new SavedCitiesActivity();
                            break;
                        case R.id.nav_log_out:
                            gotoLogIn();
                            break;
                    }
                    if(selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                        R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                .replace(R.id.fragment_container, selectedFragment)
                                .commit();

                    }
                    return true;
                }
            };

    private void gotoLogIn(){
        
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this,LogInActivity.class);
        intent.putExtra("LOG_OUT", true);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }

    public String takeDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    @Override
    public void insertCurrentDay(String result) {
        Toast.makeText(this,result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getAllCurrentDay(List<CurrentDay> currentDayList) {

    }

    @Override
    public void deleteCurrentDay(String result) {

    }

    @Override
    public void findByHourCurrentDay(CurrentDay currentDay) {

    }

    @Override
    public void findByIdCurrentDay(CurrentDay currentDay) {
        if (currentDay != null) {
            String d = currentDay.data;
            String date = takeDate();
            if (!d.contains(date)) {
                new DeleteCurrentDayOperation(MainActivity.this).execute(currentDay);
            }
            else{
                Log.d("debug","aceeasi data");
            }
        }


    }

    @Override
    public void insertResumeWeek(String result) {
        Log.d("debug",result);
    }

    @Override
    public void deleteResumeWeek(String result) {

    }

    @Override
    public void getAllResumeWeek(List<ResumeWeek> resumeWeekList) {
        if (resumeWeekList.size() > 0){
            String d = resumeWeekList.get(0).data;
            String date = takeDate();
            if (!d.contains(date)){
                for (ResumeWeek rw : resumeWeekList){
                    new DeleteResumeWeekOperation(MainActivity.this).execute(rw);
                }
            }
        }
    }

    @Override
    public void getByDateResumeWeek(ResumeWeek resumeWeek) {

    }

    @Override
    public void findByIdResumeWeek(ResumeWeek resumeWeek) {

    }
}