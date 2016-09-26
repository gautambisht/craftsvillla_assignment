package com.craftvilla.craftsvillatest.ui.activity.list;

import android.support.annotation.NonNull;

import com.craftvilla.craftsvillatest.network.RequestController;
import com.craftvilla.craftsvillatest.network.webservice.ParcelWebService;
import com.craftvilla.craftsvillatest.ui.model.ParcelModel;

import java.util.ArrayList;

import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class ParcelPresenter implements ParcelMvp.Presenter {

    private ParcelMvp.View view;

    public ParcelPresenter(@NonNull ParcelMvp.View view) {
        this.view = checkNotNull(view);
    }

    @Override
    public void loadData(final int offset, int limit, final boolean showProgress) {
        if (showProgress)
            view.showProgress();
        Single<ArrayList<ParcelModel>> call = RequestController.createService(ParcelWebService.class)
                .fetchParcels(limit, offset);
        call.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<ArrayList<ParcelModel>>() {
                    @Override
                    public void onSuccess(ArrayList<ParcelModel> parcels) {
                        if (parcels != null && parcels.size() > 0) {
                            view.hideNoItem();
                            view.updateList(parcels);
                        } else {
                            view.showNoItem();
                        }
                        if (showProgress)
                            view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (showProgress)
                            view.hideProgress();
                    }
                });
    }

    @Override
    public void itemClick(ParcelModel parcel) {
        showLocation(parcel);
    }

    @Override
    public void showLocation(ParcelModel locationModel) {
        view.showMapUi(locationModel);
    }
}
