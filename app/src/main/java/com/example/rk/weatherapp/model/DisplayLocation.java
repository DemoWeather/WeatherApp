package com.example.rk.weatherapp.model;

/**
 * Created by RK on 9/3/2016.
 */

public class DisplayLocation {
    private String full;
    private String city;
    private String state;
    private String state_name;
    private String country;
    private String country_iso3166;
    private String zip;
    private String magic;
    private String wmo;
    private String latitude;
    private String longitude;
    private String elevation;

    private DisplayLocation() {

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

    public String getState_name() {
        return state_name;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_iso3166() {
        return country_iso3166;
    }

    public String getZip() {
        return zip;
    }

    public String getMagic() {
        return magic;
    }

    public String getWmo() {
        return wmo;
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
