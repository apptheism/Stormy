package com.apptheism.stormy.ui;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptheism.stormy.R;
import com.apptheism.stormy.adapters.HourlyAdapter;
import com.apptheism.stormy.databinding.ActivityHourlyForecastBinding;
import com.apptheism.stormy.weather.Hour;

import java.util.ArrayList;
import java.util.List;


public class HourlyForecastActivity extends AppCompatActivity {

    private HourlyAdapter mAdapter;
    private ActivityHourlyForecastBinding mBinding;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        List<Hour> hoursList = (ArrayList<Hour>) intent.getSerializableExtra("HourlyList");

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_hourly_forecast);

        mAdapter = new HourlyAdapter(hoursList, this);

        mBinding.rvHourlyListItem.setAdapter(mAdapter);
        mBinding.rvHourlyListItem.setHasFixedSize(true);
        mBinding.rvHourlyListItem.addItemDecoration( new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mBinding.rvHourlyListItem.setLayoutManager(new LinearLayoutManager(this));

    }


}
