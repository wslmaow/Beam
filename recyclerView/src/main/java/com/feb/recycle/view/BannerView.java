package com.feb.recycle.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.feb.recycle.R;
import com.feb.recycle.ui.TRcyclerViewActivity;
import com.feb.recycle.utils.DensityUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BannerView extends RelativeLayout {
    RecyclerView recyclerView;
    LinearLayout points;
    LinearLayoutManager layoutManager;
    int unSelcetRes = R.drawable.shape_dots_default;
    int selectRes = R.drawable.shape_dots_select;
    CompositeDisposable compositeDisposable;
    Context context;

    public BannerView(Context context) {
        this(context,null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_holder_banner, this, true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_banner);
        points = (LinearLayout) findViewById(R.id.layout_banner_points_group);
    }

    boolean isStopScroll;
    private void startScrool(){
        isStopScroll = false;
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = Observable.timer(interval, TimeUnit.MILLISECONDS).subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isStopScroll) {
                            return;
                        }
                        isStopScroll = true;
                        recyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                    }
                });
        compositeDisposable.add(disposable);
    }


    private void setIndicators(int count, Context context) {
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }
        //初始化与个数相同的指示器点
        for (int i = 0; i < count; i++) {
            View dot = new View(context);
            dot.setBackgroundResource(unSelcetRes);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtils.dp2px(context, 5),
                    DensityUtils.dp2px(context, 5));
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }
        points.getChildAt(0).setBackgroundResource(selectRes);

    }

    List<String> images;
    long interval;


    public BannerView setBanners(List banners) {
        this.images = banners;
        return this;
    }

    public BannerView setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    public void build() {
        setIndicators(images.size(), context);

        BannerAdapter bannerAdapter = new BannerAdapter(context, images);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(bannerAdapter);
        recyclerView.scrollToPosition(images.size() * 10);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        startScrool();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int pos = layoutManager.findFirstVisibleItemPosition() % images.size(); //得到指示器红点的位置
                    for (int i = 0; i < points.getChildCount(); i++) {
                        points.getChildAt(i).setBackgroundResource(unSelcetRes);
                    }
                    points.getChildAt(pos).setBackgroundResource(selectRes);

                    if (isStopScroll){
                        startScrool();
                    }

                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    compositeDisposable.dispose();
                    isStopScroll = true;
                }
            }
        });
    }
    public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
        private List<String> list;
        private Context context;

        public BannerAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(context).load(list.get(position % list.size())).placeholder(R.drawable.bg_main_bottom).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return 1000;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.item_image);
            }
        }
    }
}
