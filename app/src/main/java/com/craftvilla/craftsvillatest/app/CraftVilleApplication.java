package com.craftvilla.craftsvillatest.app;

import android.app.Application;

import com.craftvilla.craftsvillatest.utils.NetworkMonitor;


public class CraftVilleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkMonitor.initialize(this);
    }
}
