package com.example.rk.weatherapp.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by RK on 9/3/2016.
 */

public class WeatherApplication extends Application {
    public static WeatherApplication getInstance() {
        return instance;
    }

    public static WeatherApplication instance;
    public RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        queue = Volley.newRequestQueue(instance);
    }

    public RequestQueue getQueue() {
        return queue;
    }
}
