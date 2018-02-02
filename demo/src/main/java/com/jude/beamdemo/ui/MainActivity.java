package com.jude.beamdemo.ui;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beamdemo.presenter.MainPresenter;
import com.jude.beamdemo.view.BubblePopupWindow;
import com.jude.beamdome.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr.Jude on 2016/2/22.
 */
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter> {
    @Bind(R.id.tv_hello)
    TextView tv_hello;
    BubblePopupWindow popupWindow;
    float x;
    float y;
    @Bind(R.id.iv_drawable)
    ImageView ivDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handleDrawable();

        popupWindow = new BubblePopupWindow(this);

        /*tv_hello.setClickable(true);
        tv_hello.setEnabled(true);*/
        setSpan(tv_hello.getText().toString());
        //tv_hello.setText(setSpan(tv_hello.getText().toString()));
        String uri="https://101.204.239.166/goldenBowl/rest/ioImage/bannner/5233375f791d4dd1816e5263fd470f73.png?userId=11070&token=4950b9f174cfc236b5a0a667660cc926";
        //String uri = "https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png";
        Glide.with(this)
                .load(uri)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.bg_main_bottom)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(ivDrawable);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            /*case MotionEvent.ACTION_DOWN:
                getViewLocation();
                x=event.getX();
                y=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                popupWindow.dismiss();
                tv_hello.setTranslationX(tv_hello.getX() + (event.getX() - x));
                tv_hello.setTranslationY(tv_hello.getY() + (event.getY() - y));*/

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
            //return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getViewLocation();
            popupWindow.showAt(tv_hello, Gravity.BOTTOM);
            setTouchLisener();
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "post runnable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getViewLocation() {
        int left = tv_hello.getLeft();
        int top = tv_hello.getTop();
        int right = tv_hello.getRight();
        int bottom = tv_hello.getBottom();
        float x = tv_hello.getX();
        float y = tv_hello.getY();
        float translationX = tv_hello.getTranslationX();
        float translationY = tv_hello.getTranslationY();
        float scrollX = tv_hello.getScrollX();
        float scrollY = tv_hello.getScrollY();
    }

    private void handleDrawable() {
        TransitionDrawable transitionDrawable = (TransitionDrawable) tv_hello.getBackground();
        transitionDrawable.startTransition(1500);
//        RotateDrawable rotateDrawable=(RotateDrawable) ivDrawable.getDrawable();
//        rotateDrawable.setLevel(10000);
        ivDrawable.getDrawable().mutate().setAlpha(25);
    }

    private void setTouchLisener() {
        final View contentView = popupWindow.getContentView();
        contentView.setClickable(true);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        popupWindow.update((int) (motionEvent.getRawX() - x),
                                (int) (motionEvent.getRawY() - y),
                                contentView.getWidth(), contentView.getHeight());
                        break;
                }
                return false;
            }
        });

        tv_hello.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //popupWindow.dismiss();
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv_hello.getLayoutParams();
                        params.leftMargin += motionEvent.getX() - x;
                        params.topMargin += motionEvent.getY() - y;
                        tv_hello.requestLayout();
                        break;
                    case MotionEvent.ACTION_UP:
                        getViewLocation();
                        //popupWindow.showAt(tv_hello, Gravity.BOTTOM);
                        break;
                }
                return false;
            }
        });
    }

    private void setSpan(String content){
        SpannableString spannableString=new SpannableString(content);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableString.setSpan(new RelativeSizeSpan(2.0f),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(spannableString).setSpan(new UnderlineSpan(),0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_hello.setText(spannableString);
    }

    @OnClick(R.id.tv_hello)
    public void onViewClicked() {
    }
}
