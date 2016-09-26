package com.craftvilla.craftsvillatest.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftvilla.craftsvillatest.R;
import com.craftvilla.craftsvillatest.ui.adapter.viewholder.LoadingViewHolder;
import com.craftvilla.craftsvillatest.ui.adapter.viewholder.ParcelViewHolder;
import com.craftvilla.craftsvillatest.ui.model.ParcelModel;

import java.util.ArrayList;

public class ParcelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM = 0;
    public static final int LOAD_MORE = ITEM + 1;

    private ArrayList<ParcelModel> parcels;
    private OnClickListener listener;
    private Handler handler = new Handler();

    public ParcelAdapter() {
        parcels = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parcel_adapter, parent, false);
                return new ParcelViewHolder(view);
            case LOAD_MORE:
                View loadView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
                return new LoadingViewHolder(loadView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ParcelModel parcel = parcels.get(position);


        if (viewHolder instanceof ParcelViewHolder) {
            ParcelViewHolder holder = (ParcelViewHolder) viewHolder;
            holder.applicantNameTextView.setText(parcel.getApplicant());
            holder.addressTextView.setText(parcel.getAddress());
            holder.setOnClickListener(new ParcelViewHolder.OnClickListener() {
                @Override
                public void onClick(View v, int position) {
                    if (listener != null)
                        listener.onClick(position);
                }
            });
        } else if (viewHolder instanceof LoadingViewHolder) {
            LoadingViewHolder holder = (LoadingViewHolder) viewHolder;
            holder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public int getItemViewType(int position) {
        if (parcels.get(position) == null)
            return LOAD_MORE;
        else
            return ITEM;
    }

    private int position = -1;

    public void showLoadMore() {
        parcels.add(null);
        position = parcels.size() - 1;
        handler.post(new Runnable() {
            @Override
            public void run() {
                notifyItemChanged(position);
            }
        });
    }

    public void hideLoadMore() {
        if (position > -1) {
            parcels.remove(position);
            notifyItemChanged(position);
            position = -1;
        }
    }

    public boolean isLoadMore() {
        return position > -1;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }


    public interface OnClickListener {

        void onClick(int position);
    }

    public ParcelModel get(int position) {
        return parcels.get(position);
    }

    public void addAll(ArrayList<ParcelModel> parcels) {
        this.parcels.addAll(parcels);
    }

    public void replaceAll(ArrayList<ParcelModel> parcels) {
        this.parcels.clear();
        this.parcels.addAll(parcels);
    }
}
