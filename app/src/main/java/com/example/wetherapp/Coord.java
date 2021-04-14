package com.example.wetherapp;

public class Coord {
    private String lat;
    private String lon;

    public Coord(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
