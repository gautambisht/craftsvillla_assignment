package com.craftvilla.craftsvillatest.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.craftvilla.craftsvillatest.R;


public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View loadView) {
        super(loadView);
        progressBar = (ProgressBar) loadView.findViewById(R.id.progressBar);
    }
}