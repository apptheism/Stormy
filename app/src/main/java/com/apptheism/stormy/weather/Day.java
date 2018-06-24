package com.apptheism.stormy.weather;

/**
 * Created by ${User} on ${Date}.
 */
public class Day {

    private long mTime;
    private String mSummary;
    private double mTemperatureMax;
    private String mIcon;
    private String mTimezone;

    public Day() {
    }

    public Day(long time, String summary, double temperatureMax, String icon, String timezone) {
        mTime = time;
        mSummary = summary;
        mTemperatureMax = temperatureMax;
        mIcon = icon;
        mTimezone = timezone;
    }

    public long getTime() {
        return mTime;
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

    public double getTemperatureMax() {
        return mTemperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public String getIcon() {
        return mIcon;
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
