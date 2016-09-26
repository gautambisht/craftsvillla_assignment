package com.craftvilla.craftsvillatest.ui.shared.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager;

    public RecyclerScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                onScrollStateIdle();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                onScrollStateDragging();
                break;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy >= 0) {
            //scrolling up
            onScrollUp();
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                onLoadMore();
            }
        } else {
            //scrolling down
            onScrollDown();
        }
    }

    public void onScrollStateIdle() {}

    public void onScrollStateDragging() {}

    public void onScrollUp() {}

    public void onScrollDown() {}

    public void onLoadMore() {}
}
