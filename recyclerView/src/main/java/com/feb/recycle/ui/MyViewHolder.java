package com.feb.recycle.ui;

import android.view.ViewGroup;
import android.widget.TextView;

import com.feb.recycle.R;
import com.feb.recycle.model.ResponseBean;
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
