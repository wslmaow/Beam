package com.august.databinding;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.august.databinding.databinding.ItemDataBinding;
import com.may.coomon.beans.UserBean;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    public ItemDataBinding itemDataBinding;

    public MyRecyclerViewHolder(ItemDataBinding binding) {
        super(binding.getRoot());
        this.itemDataBinding = binding;
    }

    public void bind(UserBean item) {
        itemDataBinding.setUser(item);
        itemDataBinding.executePendingBindings();
    }

}
