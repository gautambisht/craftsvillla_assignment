package com.craftvilla.craftsvillatest.ui.activity.map;

import com.craftvilla.craftsvillatest.ui.model.ParcelModel;

public class MapPresenter implements MapMvp.Presenter {

    private MapMvp.View view;

    public MapPresenter(MapMvp.View view) {
        this.view = view;
    }

    @Override
    public void showMarket(ParcelModel location) {
        view.addMarket(location);
    }
}
