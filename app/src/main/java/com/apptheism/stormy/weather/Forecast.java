package com.apptheism.stormy.weather;

import com.apptheism.stormy.R;

public class Forecast{

    private Current mCurrent;
    private Hour[] mHourlyForecast;
    private Day[] mDaylyForecast;

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current current) {
        mCurrent = current;
    }

    public Hour[] getHourlyForecast() {
        return mHourlyForecast;
    }

    public void setHourlyForecast(Hour[] hourlyForecast) {
        mHourlyForecast = hourlyForecast;
    }

    public Day[] getDaylyForecast() {
        return mDaylyForecast;
    }

    public void setDaylyForecast(Day[] daylyForecast) {
        mDaylyForecast = daylyForecast;
    }

    public static int getIconId(String iconString){

        int iconId = R.drawable.clear_day;

        switch(iconString) {
            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
            default:
                iconId = R.drawable.clear_day;

        }
        return iconId;
    }
}
