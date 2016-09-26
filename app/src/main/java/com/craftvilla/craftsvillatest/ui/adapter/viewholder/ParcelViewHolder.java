package com.craftvilla.craftsvillatest.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.craftvilla.craftsvillatest.R;

public class ParcelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView applicantNameTextView;
    public TextView addressTextView;

    private OnClickListener listener;

    public ParcelViewHolder(View itemView) {
        super(itemView);
        applicantNameTextView = (TextView) itemView.findViewById(R.id.tv_applicant_name);
        addressTextView = (TextView) itemView.findViewById(R.id.tv_applicant_address);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v, getAdapterPosition());
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {

        void onClick(View v, int position);
    }
}
