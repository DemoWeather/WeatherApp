package com.example.rk.weatherapp.model;

/**
 * Created by RK on 9/3/2016.
 */

public class CurrentObservation {

    private Image image;
    private DisplayLocation display_location;
    private ObservationLocation observation_location;
    private String station_id;
    private String observation_time;
    private String observation_time_rfc822;
    private String observation_epoch;
    private String local_time_rfc822;
    private String local_epoch;
    private String local_tz_short;
    private String local_tz_long;
    private String local_tz_offset;
    private String weather;
    private String temperature_string;
    private float temp_f;
    private float temp_c;
    private String relative_humidity;
    private String wind_string;
    private String wind_dir;
    private float wind_degrees;
    private float wind_mph;
    private String wind_gust_mph;
    private float wind_kph;
    private String wind_gust_kph;
    private String pressure_mb;
    private String pressure_in;
    private String pressure_trend;
    private String dewpoint_string;
    private float dewpoint_f;
    private float dewpoint_c;
    private String feelslike_string;
    private String feelslike_f;
    private String feelslike_c;
    private String visibility_mi;
    private String visibility_km;
    //            "solarradiation": "0",
//            "UV": "0",
//            "precip_1hr_string": "0.00 in ( 0 mm)",
//            "precip_1hr_in": "0.00",
//            "precip_1hr_metric": " 0",
    private String precip_today_string;
    private String precip_today_in;
    private String precip_today_metric;
    private String soil_moisture;
    private String icon;
//            "icon_url": "http://icons.wxug.com/i/c/k/nt_mostlycloudy.gif",
//            "forecast_url": "http://www.wunderground.com/US/KS/Shawnee_Mission.html",
//            "history_url": "http://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=KKSSTANL1",
//            "ob_url": "http://www.wunderground.com/cgi-bin/findweather/getForecast?query=38.860008,-94.682350",
//            "nowcast": ""

    private CurrentObservation() {

    }

    public Image getImage() {
        return image;
    }

    public ObservationLocation getObservation_location() {
        return observation_location;
    }

    public DisplayLocation getDisplay_location() {
        return display_location;
    }

    public String getStation_id() {
        return station_id;
    }

    public String getObservation_time() {
        return observation_time;
    }

    public String getObservation_time_rfc822() {
        return observation_time_rfc822;
    }

    public String getObservation_epoch() {
        return observation_epoch;
    }

    public String getLocal_time_rfc822() {
        return local_time_rfc822;
    }

    public String getLocal_epoch() {
        return local_epoch;
    }

    public String getLocal_tz_short() {
        return local_tz_short;
    }

    public String getLocal_tz_long() {
        return local_tz_long;
    }

    public String getLocal_tz_offset() {
        return local_tz_offset;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperature_string() {
        return temperature_string;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public String getRelative_humidity() {
        return relative_humidity;
    }

    public String getWind_string() {
        return wind_string;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public float getWind_degrees() {
        return wind_degrees;
    }

    public float getWind_mph() {
        return wind_mph;
    }

    public String getWind_gust_mph() {
        return wind_gust_mph;
    }

    public String getWind_gust_kph() {
        return wind_gust_kph;
    }

    public float getWind_kph() {
        return wind_kph;
    }

    public String getPressure_mb() {
        return pressure_mb;
    }

    public String getPressure_trend() {
        return pressure_trend;
    }

    public String getPressure_in() {
        return pressure_in;
    }

    public String getDewpoint_string() {
        return dewpoint_string;
    }

    public float getDewpoint_f() {
        return dewpoint_f;
    }

    public float getDewpoint_c() {
        return dewpoint_c;
    }

    public String getFeelslike_string() {
        return feelslike_string;
    }

    public String getFeelslike_f() {
        return feelslike_f;
    }

    public String getFeelslike_c() {
        return feelslike_c;
    }

    public String getVisibility_mi() {
        return visibility_mi;
    }

    public String getVisibility_km() {
        return visibility_km;
    }

    public String getPrecip_today_string() {
        return precip_today_string;
    }

    public String getPrecip_today_in() {
        return precip_today_in;
    }

    public String getPrecip_today_metric() {
        return precip_today_metric;
    }

    public String getSoil_moisture() {
        return soil_moisture;
    }

    public String getIcon() {
        return icon;
    }
}
