package com.example.rk.weatherapp.model;

/**
 * Created by RK on 9/3/2016.
 */

public class ObservationLocation {

    private String full ;
    private String city ;
    private String state ;
    private String country ;
    private String country_iso3166 ;
    private String latitude;
    private String longitude;
    private String elevation;

    private ObservationLocation(){

    }
    public String getFull() {
        return full;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_iso3166() {
        return country_iso3166;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getElevation() {
        return elevation;
    }

}
