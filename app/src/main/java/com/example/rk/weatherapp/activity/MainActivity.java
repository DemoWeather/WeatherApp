package com.example.rk.weatherapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ZipCodeAdapter.OnClickViewHolder {
    public static final String TAG = MainActivity.class.getSimpleName();
    List<String> zipCodes = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    ZipCodeAdapter zipCodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zipCodes.add("66223");
        zipCodes.add("21043");
        zipCodes.add("78575");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        zipCodeAdapter = new ZipCodeAdapter(zipCodes);
        zipCodeAdapter.setOnClickViewHolder(this);
        recyclerView.setAdapter(zipCodeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZipCodeDialog();
            }
        });
    }

    /**
     * Dialog to add zip code to the list
     */
    private void addZipCodeDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Add Zip Code");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                zipCodes.add(edt.getText().toString());
                zipCodeAdapter.notifyDataSetChanged();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    /**
     * Dialog to delete the selected zipcode
     * @param zipCode string value of zipcode that needs to be deleted
     */
    public void showDeleteDialog(final String zipCode) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Remove Zip Code");
        dialogBuilder.setMessage("Are you sure you want to remove ?");
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                zipCodes.remove(zipCode);
                zipCodeAdapter.notifyDataSetChanged();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public void performRequest(final String zipCode) {
        final Gson gson = new Gson();
        String url = "http://api.wunderground.com/api/f5c35403c02f8eae/conditions/q/" + zipCode + ".json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("activity", response);
                WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
                CurrentObservation currentObservation =
                        weatherResponse.getCurrent_observation();
                if (currentObservation == null) {
                    Toast toast = Toast
                            .makeText(getApplicationContext(), "Zip Code is invalid", Toast.LENGTH_LONG);

                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), WeatherDetailsActivity.class);
                    intent.putExtra("temp", currentObservation.getTemperatureString());
                    intent.putExtra("city", currentObservation.getDisplay_location().getFull());
                    intent.putExtra("feelsLike", currentObservation.getFeelslikeString());

                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error in fetching the response");

            }
        });
        WeatherApplication.getInstance().getQueue().add(stringRequest);
    }

    @Override
    public void onLongClick(String zipCode) {
        showDeleteDialog(zipCode);
    }
}
