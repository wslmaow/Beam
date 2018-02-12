package com.jude.beamdemo.ui;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.beamdemo.model.MyModel;
import com.jude.beamdemo.model.ResponseBean;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/11.
 */

class MyViewHolder extends BaseViewHolder<ResponseBean> {

    @Bind(R.id.healthProductName)
    TextView healthProductName;
    @Bind(R.id.orderOrganization)
    TextView orderOrganization;
    @Bind(R.id.orderUser)
    TextView orderUser;
    @Bind(R.id.phone)
    TextView phone;

    public MyViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_my_model);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(ResponseBean data) {
        super.setData(data);
        healthProductName.setText(data.healthProductName);
        orderOrganization.setText(data.orderOrganization);
        orderUser.setText(data.orderUser);
        phone.setText(data.phone);
    }
}
