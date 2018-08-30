package com.august.databinding;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.august.databinding.databinding.ItemDataBinding;

import com.may.coomon.beans.UserBean;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
    Context context;
    List<UserBean> datas;

    public MyRecyclerAdapter(Context context,List<UserBean> datas) {

        this.context = context;
        this.datas = datas;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.bind(datas.get(position));
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDataBinding itemBinding = ItemDataBinding.inflate(layoutInflater, parent, false);
        return new MyRecyclerViewHolder(itemBinding);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
