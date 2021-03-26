package com.example.wetherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedCitiesAdapter extends RecyclerView.Adapter<SavedCitiesAdapter.SavedCitiesViewHolder> {

    private List<String> dataset;

    public SavedCitiesAdapter(List<String> data){
        dataset = data;
    }
    @NonNull
    @Override
    public SavedCitiesAdapter.SavedCitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city,parent,false);
        return new SavedCitiesAdapter.SavedCitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedCitiesAdapter.SavedCitiesViewHolder holder, int position) {
        holder.bind(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class SavedCitiesViewHolder extends RecyclerView.ViewHolder{

        private final TextView city;

        public SavedCitiesViewHolder(View view){
            super(view);
            city = view.findViewById(R.id.city);
        }
        public void bind(String item){
            city.setText(item);
        }
    }

}
