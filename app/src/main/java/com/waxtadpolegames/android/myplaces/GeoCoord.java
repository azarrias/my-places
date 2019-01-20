package com.waxtadpolegames.android.myplaces;

public class GeoCoord {

    private double longitude, latitude;
    static final double EARTH_RADIUS = 6371000; // in meters

    public GeoCoord(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return new String("longitude:" + longitude + ", latitude:" + latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double distance(GeoCoord other) {
        // Haversine formula calculates shortest distance over the earth's surface
        double lat1 = Math.toRadians(other.latitude);
        double lat2 = Math.toRadians(latitude);
        double deltaLat = Math.toRadians(latitude - other.latitude);
        double deltaLon = Math.toRadians(longitude - other.longitude);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2) *
                Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * EARTH_RADIUS;
    }
}
