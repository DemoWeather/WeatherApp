package com.example.rk.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rk.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RK on 9/3/2016.
 */

public class ZipCodeAdapter extends RecyclerView.Adapter<ZipCodeAdapter.ZipCodeViewHolder> {
    private static final String TAG = ZipCodeAdapter.class.getSimpleName();
    private List<String> zipCodes = new ArrayList<>();
    private OnClickViewHolder onClickViewHolder;

    public ZipCodeAdapter(List<String> zipCodes) {
        this.zipCodes = zipCodes;
    }

    public interface OnClickViewHolder {
        void performRequest(String zipCode);

        void onLongClick(String zipCode);
    }

    @Override
    public ZipCodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "on create view holder");
        ZipCodeViewHolder viewHolder;
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View itemView = inflater.inflate(R.layout.itemview_layout, parent, false);
        viewHolder = new ZipCodeViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ZipCodeViewHolder holder, final int position) {
        Log.d(TAG, "on bind view holder");
        Log.e(TAG, zipCodes.get(position));
        final String zipCode = zipCodes.get(position);
        holder.txtViewTitle.setText(zipCode);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickViewHolder.performRequest(zipCode);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClickViewHolder.onLongClick(zipCode);
                return false;
            }
        });
    }

    public void setOnClickViewHolder(OnClickViewHolder onClickViewHolder) {
        this.onClickViewHolder = onClickViewHolder;
    }

    @Override
    public int getItemCount() {
        return zipCodes.size();
    }

    static class ZipCodeViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewTitle;

        ZipCodeViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.zip_code);
        }
    }


}
