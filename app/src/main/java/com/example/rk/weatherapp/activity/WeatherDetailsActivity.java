package com.example.rk.weatherapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rk.weatherapp.R;

/**
 * Created by RK on 9/3/2016.
 */

public class WeatherDetailsActivity extends AppCompatActivity {
    private TextView temperature;
    private TextView city;
    private TextView feelsLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.weather_details_main);
        temperature = (TextView) findViewById(R.id.edit1);
        city = (TextView) findViewById(R.id.city);
        feelsLike = (TextView) findViewById(R.id.feels_like_value);
    }

    @Override
    protected void onResume() {
        super.onResume();
        city.setText((String) getIntent().getExtras().get("city"));
        temperature.setText((String) getIntent().getExtras().get("temp"));
        feelsLike.setText((String) getIntent().getExtras().get("feelsLike"));
    }
}
