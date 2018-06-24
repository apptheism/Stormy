package com.apptheism.stormy.weather;

/**
 * Created by ${User} on ${Date}.
 */
public class CurrentLocation {

    private String locationLabel;

    private long latitude;
    private long longitude;

    public CurrentLocation() {
    }

    public CurrentLocation(String locationLabel, long latitude, long longitude) {
        this.locationLabel = locationLabel;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocationLabel() {
        return locationLabel;
    }

    public void setLocationLabel(String locationLabel) {
        this.locationLabel = locationLabel;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

}
