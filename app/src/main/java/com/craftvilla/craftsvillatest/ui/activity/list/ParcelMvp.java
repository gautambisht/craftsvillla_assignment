package com.craftvilla.craftsvillatest.ui.activity.list;

import com.craftvilla.craftsvillatest.ui.model.ParcelModel;

import java.util.ArrayList;


public class ParcelMvp {

    interface Presenter {

        void itemClick(ParcelModel parcel);

        void loadData(int offset, int limit, boolean showProgress);

        void showLocation(ParcelModel locationModel);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void updateList(ArrayList<ParcelModel> parcels);

        void showNoItem();

        void hideNoItem();

        void showMapUi(ParcelModel locationModel);

    }
}
