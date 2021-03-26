package com.example.wetherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment {
    public static List<DayOfWeekModel> weekList = new ArrayList<>();
    public HomePage() {
        super(R.layout.home_page);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater,container,savedInstanceState);

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
