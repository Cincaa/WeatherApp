package com.example.wetherapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SavedCitiesActivity extends Activity {
    ListView listView;
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

    }
}
