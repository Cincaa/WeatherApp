package com.example.wetherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends Fragment implements CurrentDayOperations,ResumeWeekOperations{
    public Button button;
    public TextView city;
    public TextView time;
    public TextView temp;
    public ImageView img;
    public static List<DayOfWeekModel> weekList = new ArrayList<>();
    public HomePage() {
        super(R.layout.home_page);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
        View v = super.onCreateView(inflater,container,savedInstanceState);

        city = v.findViewById(R.id.City);
        time = v.findViewById(R.id.Time);
        temp = v.findViewById(R.id.Temperature);
        img = v.findViewById(R.id.imgIcon);
        city.setText("Bucharest");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH");
        String currentHour = dateFormat.format(date);
        time.setText(currentHour+":00");
        String hour = String.valueOf(3*(Integer.valueOf(currentHour)/3));
        new FindByHourCurrentDayOperation(this).execute(hour);

        button = v.findViewById(R.id.See_more);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right,R.anim.exit_left_to_right);
                fragmentTransaction.replace(R.id.home_page,new SeeMoreActivity());
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //TODO vezi ca nu se face weeklist
        super.onViewCreated(view, savedInstanceState);
        new GetAllResumeWeekOperation(this).execute(new Object());
        RecyclerView rv = view.findViewById(R.id.recycle_view_resume_week);
        DayOfWeekAdapter adapter = new DayOfWeekAdapter(weekList);
        rv.setAdapter(adapter);
    }

    @Override
    public void insertCurrentDay(String result) {

    }

    @Override
    public void getAllCurrentDay(List<CurrentDay> currentDayList) {

    }

    @Override
    public void deleteCurrentDay(String result) {

    }

    @Override
    public void findByHourCurrentDay(CurrentDay currentDay) {
        if (currentDay != null){
            temp.setText(currentDay.temp+"°C");
            String desc = currentDay.description;
            //todo vezi cum modifici pozele in fct de descriere
            /*switch (desc){
                case "Clouds":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.clouds,getActivity().getApplicationContext().getTheme()));
                    break;
                case "Rain":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.rain,getActivity().getApplicationContext().getTheme()));
                    break;
                case "Snow":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.snow,getActivity().getApplicationContext().getTheme()));
                    break;
                case "Clear":
                    img.setImageDrawable(getResources().getDrawable(R.drawable.sun,getActivity().getApplicationContext().getTheme()));
                    break;
            }*/
        }

    }

    @Override
    public void insertResumeWeek(String result) {

    }

    @Override
    public void deleteResumeWeek(String result) {

    }

    @Override
    public void getAllResumeWeek(List<ResumeWeek> resumeWeekList) {
        for(ResumeWeek rw : resumeWeekList)
        {
            DayOfWeekModel dw = new DayOfWeekModel(rw.day,rw.tempMin,rw.tempMax);
            weekList.add(dw);
        }
    }

    @Override
    public void getByDateResumeWeek(ResumeWeek resumeWeek) {

    }
}
