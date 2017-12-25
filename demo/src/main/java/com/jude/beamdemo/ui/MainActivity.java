package com.jude.beamdemo.ui;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beamdemo.adapter.MyPagerAdapter;
import com.jude.beamdemo.presenter.MainPresenter;
import com.jude.beamdemo.view.NewEditText;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.yuyh.library.BubblePopupWindow;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Mr.Jude on 2016/2/22.
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {
    ArrayList<String> data=new ArrayList();
    TextView tv_hello;
    ViewPager viewPager;
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hello=(TextView) findViewById(R.id.tv_hello);
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        indicator=(CircleIndicator) findViewById(R.id.indicator);
        initViewpager();
        data.add("aaa");
        data.add("bbb");
        data.add("ccc");
        Observable.fromIterable(data).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            com.jude.beamdemo.view.BubblePopupWindow popupWindow=new com.jude.beamdemo.view.BubblePopupWindow(this);
            popupWindow.showAt(tv_hello,Gravity.BOTTOM);
        }
    }
    private void initViewpager(){
        ImageView view=new ImageView(this);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setBackgroundColor(Color.parseColor("#440093ff"));

        ArrayList<View> contemts=new ArrayList<>();
        contemts.add(view);
        ImageView view1=new ImageView(this);
        ViewGroup.LayoutParams params1=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view1.setLayoutParams(params1);
        view1.setBackgroundColor(Color.parseColor("#ee9cff"));
        contemts.add(view1);
        ImageView view2=new ImageView(this);
        ViewGroup.LayoutParams params2=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view2.setLayoutParams(params2);
        view2.setBackgroundColor(Color.parseColor("#ee9c00"));
        contemts.add(view2);

        viewPager.setAdapter(new MyPagerAdapter(contemts));
        indicator.setViewPager(viewPager);
    }
}
