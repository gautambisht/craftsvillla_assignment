package com.craftvilla.craftsvillatest.ui.activity.map;

import com.craftvilla.craftsvillatest.ui.model.ParcelModel;


public class MapMvp {

    public interface Presenter {

        void showMarket(ParcelModel location);
    }

    public interface View {

        void addMarket(ParcelModel location);
    }
}
