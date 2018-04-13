package com.apr.coordinatorLayout.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apr.coordinatorLayout.R;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
    }


}
