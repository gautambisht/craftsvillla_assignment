package com.craftvilla.craftsvillatest.network.webservice;

import com.craftvilla.craftsvillatest.ui.model.ParcelModel;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface ParcelWebService {

    String FETCH_PARCELS = "resource/6a9r-agq8.json?";

    @GET(FETCH_PARCELS)
    Single<ArrayList<ParcelModel>> fetchParcels(@Query("$limit") int limit, @Query("$offset") int offset);
}
