package com.feb.recycle.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feb.recycle.R;
import com.feb.recycle.utils.DensityUtils;
import com.feb.recycle.utils.LogUtils;
import com.feb.recycle.view.BannerView;
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
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    List<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_recyclerview_activity);
        ButterKnife.bind(this);

        tRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        tRecyclerview.setAdapter(getAdapetr());
        final List<Object> data = new ArrayList<>();

        images.add("http://pic.616pic.com/bg_w1180/00/26/43/f70d6qrZnI.jpg!/fh/300");
        images.add("http://pic.616pic.com/bg_w1180/00/10/52/djRwP5S3EJ.jpg!/fw/1120");
        images.add("http://pic.616pic.com/bg_w1180/00/03/77/dZqXhfoNsg.jpg!/fh/300");
        images.add("http://pic.616pic.com/bg_w1180/00/03/32/ZMmnErVcVd.jpg!/fw/1120");

        data.add("title");
        data.add(images);

        loadManager = new LoadManager.Builder().
                setViewParams(this)
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
                .bind(List.class,new BannerViewHolder(this))
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

    class BannerViewHolder extends AbsViewHolder<List,BaseHolder>{
        Context context;
        BannerView bannerView;

        public BannerViewHolder(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public BaseHolder createViewHolder(View view) {
            return new BaseHolder(view);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.banner;
        }

        @Override
        protected void onBindViewHolder(@NonNull BaseHolder baseHolder, @NonNull final List list) {
            bannerView = baseHolder.getViewById(R.id.banner);
            bannerView.setBanners(list).setInterval(2000).build();
        }

    }




}
