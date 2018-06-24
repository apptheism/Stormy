package com.apptheism.stormy.weather;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Hour implements Serializable {

    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimezone;

    public Hour() {
    }

    public Hour(long time, String summary, double temperature, String icon, String timezone) {
        mTime = time;
        mSummary = summary;
        mTemperature = temperature;
        mIcon = icon;
        mTimezone = timezone;
    }

    public long getTime(){
        return mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        return formatter.format(new Date(mTime*1000));
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        // Fahrenheit to Celsius
        this.mTemperature = Math.round( (temperature - 32) * 5 / 9);
    }

    public int getIcon() {
        return Forecast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }
}
