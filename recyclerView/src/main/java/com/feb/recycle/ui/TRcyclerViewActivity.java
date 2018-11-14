package com.feb.recycle.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feb.recycle.R;
import com.jude.beam.expansion.BeamBaseActivity;
import com.tqzhang.stateview.core.LoadManager;
import com.trecyclerview.TRecyclerView;
import com.trecyclerview.holder.AbsViewHolder;
import com.trecyclerview.holder.BaseHolder;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.HeaderVo;
import com.trecyclerview.progressindicator.ProgressStyle;
import com.trecyclerview.view.HeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TRcyclerViewActivity extends BeamBaseActivity {
    @Bind(R.id.t_recyclerview)
    TRecyclerView tRecyclerview;

    LoadManager loadManager;
    @Bind(R.id.tv_test)
    TextView tvTest;
    @Bind(R.id.tv_test2)
    TextView tvTest2;
    @Bind(R.id.ll_tvs)
    LinearLayout llTvs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_recyclerview_activity);
        ButterKnife.bind(this);

        tRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        tRecyclerview.setAdapter(getAdapetr());
        final List<Object> data = new ArrayList<>();
        data.add("sb");

        loadManager = new LoadManager.Builder().
                setViewParams(llTvs)
                .build();
        tRecyclerview.refreshComplete(data, true);

        llTvs.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadManager.showSuccess();
            }
        }, 3000);
    }

    MultiTypeAdapter getAdapetr() {
        return new MultiTypeAdapter.Builder<>()
                .bind(HeaderVo.class, new HeaderViewHolder(this, ProgressStyle.Pacman))
                .bind(String.class, new TitleViewHolder(this))
                .build();
    }

    class TitleViewHolder extends AbsViewHolder<String, BaseHolder> {
        public TitleViewHolder(Context context) {
            super(context);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.title;
        }

        @Override
        protected void onBindViewHolder(@NonNull BaseHolder baseHolder, @NonNull String s) {
            TextView view = baseHolder.getViewById(R.id.tv_title);
            view.setText("title===" + s);
        }

        @Override
        public BaseHolder createViewHolder(View view) {
            return new BaseHolder(view);
        }


    }


}
