package com.example.wetherapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavedCitiesActivity extends Fragment {

    public static List<String> citiesList = new ArrayList<>();
    public SavedCitiesActivity() {
        super(R.layout.saved_cities);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListCity();
        RecyclerView rv = view.findViewById(R.id.cities_list);
        SavedCitiesAdapter adapter = new SavedCitiesAdapter(citiesList);
        rv.setAdapter(adapter);
    }

    private void initListCity(){
        citiesList.add("Piatra Neamt");
        citiesList.add("Bucuresti");
    }
    /*ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_cities);

        listView = findViewById(R.id.citiesList);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Piatra Neamt");
        arrayList.add("Bucuresti");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }*/
}
