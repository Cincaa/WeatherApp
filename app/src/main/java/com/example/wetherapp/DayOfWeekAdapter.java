package com.example.wetherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.DayOfWeek;
import java.util.List;

public class DayOfWeekAdapter extends RecyclerView.Adapter<DayOfWeekAdapter.DayOfWeekViewHolder> {

    private List<DayOfWeekModel> dataset;

    public void submit(List<DayOfWeekModel> newlist){
        dataset = newlist;
        notifyDataSetChanged();
}   public DayOfWeekAdapter(List<DayOfWeekModel> data){
        dataset = data;
    }
    @NonNull
    @Override
    public DayOfWeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_of_week,parent,false);
        return new DayOfWeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayOfWeekViewHolder holder, int position) {
        holder.bind(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DayOfWeekViewHolder extends RecyclerView.ViewHolder{

        private final TextView day;
        private final TextView minMaxTemperature;

        public DayOfWeekViewHolder(View view){
            super(view);
            day = view.findViewById(R.id.day);
            minMaxTemperature = view.findViewById(R.id.min_max_temp);
        }
        public void bind(DayOfWeekModel item){
            day.setText(item.getDay());
            minMaxTemperature.setText(item.getMinTemp()+'/'+item.getMaxTemp());

        }
    }

}
