package com.apr.coordinatorLayout.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.apr.coordinatorLayout.R;
import com.apr.coordinatorLayout.holder.MyViewHolder;
import com.apr.coordinatorLayout.ui.TestCoordinatorActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class OptimizedRecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public OptimizedRecyclerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_text,item)
              .addOnClickListener(R.id.item_text);
    }
}
