package com.craftvilla.craftsvillatest.ui.activity.map;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.craftvilla.craftsvillatest.R;
import com.craftvilla.craftsvillatest.ui.activity.base.BaseActivity;
import com.craftvilla.craftsvillatest.ui.model.ParcelModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, MapMvp.View {

    public static final String EXTRA_PARCEL_JSON = "parcelJson";
    private GoogleMap mMap;
    private MapMvp.Presenter presenter;
    private ParcelModel parcel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
        presenter = new MapPresenter(this);
        if (getIntent() != null && getIntent().hasExtra(EXTRA_PARCEL_JSON)) {
            String json = getIntent().getStringExtra(EXTRA_PARCEL_JSON);
            parcel = new Gson().fromJson(json, ParcelModel.class);
        }
    }

    private void init() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.parcel_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (parcel != null)
            presenter.showMarket(parcel);
    }

    private void zoomCamera(LatLng location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showToast("Clicked " + marker.getTitle());
        return true;
    }

    @Override
    public void addMarket(ParcelModel location) {
        LatLng latLng = new LatLng(location.getLocation().getCoordinates().get(0),
                location.getLocation().getCoordinates().get(1));
        mMap.addMarker(new MarkerOptions()
                .position(latLng).title(location.getAddress()));
        mMap.setOnMarkerClickListener(this);
        zoomCamera(latLng);
    }
}
