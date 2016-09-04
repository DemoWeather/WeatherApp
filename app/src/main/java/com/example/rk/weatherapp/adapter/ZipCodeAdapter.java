package com.example.rk.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rk.weatherapp.R;
import com.example.rk.weatherapp.application.WeatherApplication;
import com.example.rk.weatherapp.model.WeatherResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RK on 9/3/2016.
 */

public class ZipCodeAdapter extends RecyclerView.Adapter<ZipCodeAdapter.ZipCodeViewHolder> {
    public static  final String TAG = ZipCodeAdapter.class.getSimpleName();
    private ArrayList<String> zipCodes = new ArrayList<>();
    private OnClickViewHolder onClickViewHolder;

    public ZipCodeAdapter(ArrayList<String> zipCodes) {
        this.zipCodes = zipCodes;
    }

public interface OnClickViewHolder{
     void performRequest(String zipCode);
}
    @Override
    public ZipCodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG,"on create view holder");
        ZipCodeViewHolder viewHolder;
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.itemview_layout, parent, false);
        viewHolder = new ZipCodeViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ZipCodeViewHolder holder, final int position) {
        Log.e(TAG,"on bind view holder");
        Log.e(TAG,zipCodes.get(position));
        final String zipCode = zipCodes.get(position);
        holder.txtViewTitle.setText(zipCode);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickViewHolder.performRequest(zipCode);
            }
        });
    }
    public  void  setOnClickViewHolder(OnClickViewHolder onClickViewHolder){
        this.onClickViewHolder = onClickViewHolder;
    }

    @Override
    public int getItemCount() {
       return zipCodes.size();
    }

    public static class ZipCodeViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewTitle;

        ZipCodeViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.zip_code);
        }
    }



}
