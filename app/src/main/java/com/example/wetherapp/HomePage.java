package com.example.wetherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePage extends Fragment {
    public Button button;
    public TextView city;
    public TextView time;
    public TextView temp;
    public static List<DayOfWeekModel> weekList = new ArrayList<>();
    public HomePage() {
        super(R.layout.home_page);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View v = super.onCreateView(inflater,container,savedInstanceState);
        city = v.findViewById(R.id.City);
        time = v.findViewById(R.id.Time);
        temp = v.findViewById(R.id.Temperature);
        city.setText(bundle.getString("city"));
        button = v.findViewById(R.id.See_more);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.home_page,new SeeMoreActivity());
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initListDayOfWeek();
        RecyclerView rv = view.findViewById(R.id.recycle_view_resume_week);
        DayOfWeekAdapter adapter = new DayOfWeekAdapter(weekList);
        rv.setAdapter(adapter);
    }

    private void initListDayOfWeek(){
        weekList.add(new DayOfWeekModel("Today","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Luni","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Marti","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Miercuri","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Joi","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Vineri","0°C","3°C"));
        weekList.add(new DayOfWeekModel("Sambata","0°C","3°C"));
    }


}
