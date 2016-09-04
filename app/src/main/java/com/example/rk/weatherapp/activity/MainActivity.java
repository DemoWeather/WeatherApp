package com.example.rk.weatherapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rk.weatherapp.R;
import com.example.rk.weatherapp.adapter.ZipCodeAdapter;
import com.example.rk.weatherapp.application.WeatherApplication;
import com.example.rk.weatherapp.model.CurrentObservation;
import com.example.rk.weatherapp.model.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ZipCodeAdapter.OnClickViewHolder {
    ArrayList<String> zipCodes = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    ZipCodeAdapter zipCodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Gson gson = new Gson();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        zipCodes.add("66223");
        zipCodes.add("21043");
        zipCodes.add("78575");

    }

    @Override
    protected void onResume() {
        super.onResume();
        zipCodeAdapter = new ZipCodeAdapter(zipCodes);
        zipCodeAdapter.setOnClickViewHolder(this);
        recyclerView.setAdapter(zipCodeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showChangeLangDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                zipCodes.add(edt.getText().toString());
                Log.e("ACT", zipCodes.toString());
                zipCodeAdapter.notifyDataSetChanged();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                dialog.dismiss();

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public void performRequest(String zipCode) {
        final Gson gson = new Gson();
        String url = "http://api.wunderground.com/api/f5c35403c02f8eae/conditions/q/" + zipCode + ".json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("acivity", "successful");
                Log.e("activity", response);
                WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
                CurrentObservation currentObservation =
                        weatherResponse.getCurrent_observation();
                if (currentObservation == null) {
                    Toast toast = Toast
                            .makeText(getApplicationContext(), "Zip Code is invalid", Toast.LENGTH_LONG);

                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), AddZipCodeActivity.class);
                    intent.putExtra("temp", currentObservation.getTemperature_string());
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("act", "failed");

            }
        });
        WeatherApplication.getInstance().getQueue().add(stringRequest);
    }
}
