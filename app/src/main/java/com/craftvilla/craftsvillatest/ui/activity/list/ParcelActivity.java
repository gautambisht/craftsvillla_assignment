package com.craftvilla.craftsvillatest.ui.activity.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.craftvilla.craftsvillatest.R;
import com.craftvilla.craftsvillatest.ui.activity.base.BaseActivity;
import com.craftvilla.craftsvillatest.ui.activity.map.MapActivity;
import com.craftvilla.craftsvillatest.ui.adapter.ParcelAdapter;
import com.craftvilla.craftsvillatest.ui.model.LocationModel;
import com.craftvilla.craftsvillatest.ui.model.ParcelModel;
import com.craftvilla.craftsvillatest.ui.shared.view.RecyclerScrollListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ParcelActivity extends BaseActivity implements ParcelMvp.View {

    public static final int LIMIT = 10;
    private RecyclerView recyclerView;
    private TextView noItemTextView;
    private ParcelAdapter adapter;
    private ParcelMvp.Presenter presenter;

    private int offset;
    private boolean noMoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcels);
        init();
        presenter = new ParcelPresenter(this);
        presenter.loadData(offset, LIMIT, true);
    }

    private void init() {
        noItemTextView = (TextView) findViewById(R.id.tv_no_items);
        recyclerView = (RecyclerView) findViewById(R.id.rv_parcels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ParcelAdapter();
        adapter.setOnClickListener(new ParcelAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                presenter.itemClick(adapter.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                if (!noMoreData && !adapter.isLoadMore()) {
                    adapter.showLoadMore();
                    offset += LIMIT;
                    presenter.loadData(offset, LIMIT, false);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        showHud();
    }

    @Override
    public void hideProgress() {
        hideHud();
    }

    @Override
    public void updateList(ArrayList<ParcelModel> parcels) {
        if (offset == 0) {
            adapter.replaceAll(parcels);
        } else {
            adapter.addAll(parcels);
        }
        adapter.hideLoadMore();
        adapter.notifyDataSetChanged();
        if (parcels != null && parcels.size() < LIMIT)
            noMoreData = true;
    }

    @Override
    public void showNoItem() {
        recyclerView.setVisibility(View.GONE);
        noItemTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItem() {
        recyclerView.setVisibility(View.VISIBLE);
        noItemTextView.setVisibility(View.GONE);
    }

    @Override
    public void showMapUi(ParcelModel locationModel) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(MapActivity.EXTRA_PARCEL_JSON, new Gson().toJson(locationModel));
        startActivity(intent);
    }
}
