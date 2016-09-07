package com.example.rk.weatherapp.application;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by RK on 9/3/2016.
 */

public class WeatherApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static final String TAG = WeatherApplication.class.getSimpleName();
    private static WeatherApplication instance;
    private RequestQueue queue;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        instance = this;
        queue = Volley.newRequestQueue(instance);
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public static WeatherApplication getInstance() {
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(TAG, " fatal crash occured");
        if (defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        }

    }
}
