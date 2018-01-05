package com.jude.beamdemo.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beamdemo.presenter.MainPresenter;
import com.jude.beamdemo.view.BubblePopupWindow;
import com.jude.beamdome.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Jude on 2016/2/22.
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {
    @Bind(R.id.tv_hello)
    TextView tv_hello;
    BubblePopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        popupWindow = new BubblePopupWindow(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getViewLocation();
                break;
            case MotionEvent.ACTION_MOVE:
                popupWindow.dismiss();
                float x=event.getX();
                float y=event.getY();

                /*AnimatorSet set=new AnimatorSet();
                ObjectAnimator animator=ObjectAnimator.ofFloat(tv_hello,"translationX",
                        tv_hello.getX(),x);
                ObjectAnimator animator2=ObjectAnimator.ofFloat(tv_hello,"translationY"
                        ,tv_hello.getY(),y);
                set.setDuration(100).playTogether(animator,animator2);
                set.start();
                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        popupWindow.showAt(tv_hello, Gravity.BOTTOM);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });*/
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getViewLocation();
            BubblePopupWindow popupWindow = new BubblePopupWindow(this);
//            popupWindow.showAt(tv_hello, Gravity.BOTTOM);
        }
    }
    private void getViewLocation(){
        int left = tv_hello.getLeft();
        int top = tv_hello.getTop();
        int right = tv_hello.getRight();
        int bottom = tv_hello.getBottom();
        float x = tv_hello.getX();
        float y = tv_hello.getY();
        float translationX = tv_hello.getTranslationX();
        float translationY = tv_hello.getTranslationY();
        float scrollX=tv_hello.getScrollX();
        float scrollY=tv_hello.getScrollY();
    }
}
