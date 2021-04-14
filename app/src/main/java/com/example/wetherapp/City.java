package com.example.wetherapp;

public class City {
    private String name;
    private Coord coord;
    private String country;

    public City(String name, Coord coord, String country) {
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

}
