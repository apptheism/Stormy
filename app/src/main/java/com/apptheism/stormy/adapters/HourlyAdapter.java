package com.apptheism.stormy.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptheism.stormy.R;
import com.apptheism.stormy.databinding.HourlyListItemBinding;
import com.apptheism.stormy.weather.Hour;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    private List<Hour> mHours;
    private Context mContext;

    public HourlyAdapter(List<Hour> hours, Context context) {
        mHours = hours;
        mContext = context;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HourlyListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.hourly_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hour hour = mHours.get(position);
        holder.mHourlyListItemBinding.setHour(hour);
    }

    @Override
    public int getItemCount() {
        return mHours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public HourlyListItemBinding mHourlyListItemBinding;


        public ViewHolder(HourlyListItemBinding hourlyLLayoutBinding) {
            super(hourlyLLayoutBinding.getRoot());

            mHourlyListItemBinding = hourlyLLayoutBinding;
        }
    }
}
