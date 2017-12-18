package com.jude.beamdemo.ui;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beamdemo.presenter.MainPresenter;
import com.jude.beamdemo.view.NewEditText;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.yuyh.library.BubblePopupWindow;

import java.util.ArrayList;

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

/**
 * Created by Mr.Jude on 2016/2/22.
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {
    ArrayList<String> data=new ArrayList();
    TextView tv_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hello=(TextView) findViewById(R.id.tv_hello);
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
            NewEditText newEditText=new NewEditText(this);
            FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            BubblePopupWindow bubblePopupWindow=new BubblePopupWindow(this);
            newEditText.setLayoutParams(params);
            View view= LayoutInflater.from(this).inflate(R.layout.buble_pop,null);
            bubblePopupWindow.setBubbleView(view);
            bubblePopupWindow.setBackgroundDrawable(new BitmapDrawable());
            bubblePopupWindow.setOutsideTouchable(true);
            bubblePopupWindow.setFocusable(false);
            bubblePopupWindow.show(tv_hello, Gravity.BOTTOM,120);
        }
    }
}
