package com.feb.demo.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.feb.demo.BuildConfig;
import com.feb.demo.MyApplication;
import com.feb.demo.R;
import com.feb.demo.presenter.MainPresenter;
import com.feb.demo.utils.BitmapUtils;
import com.feb.demo.utils.WindowUtil;
import com.feb.demo.view.BubblePopupWindow;
import com.github.chrisbanes.photoview.PhotoView;
import com.jude.beam.bijection.FloatWindoeEvent;
import com.jude.beam.bijection.HideFloatWindowEvent;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    PhotoView ivDrawable;
    @Bind(R.id.iv_drawable2)
    ImageView ivDrawable2;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.btn_on_off)
    TextView btnOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handleDrawable();
        setWindmill();

        ivDrawable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

        if (BuildConfig.FLAVOR.equals("app2")) {
            radioGroup.setVisibility(View.GONE);
            ivDrawable2.setVisibility(View.GONE);
            btnOnOff.setVisibility(View.GONE);
        } else {
            radioGroup.setVisibility(View.VISIBLE);
            ivDrawable2.setVisibility(View.VISIBLE);
            btnOnOff.setVisibility(View.VISIBLE);
        }

        popupWindow = new BubblePopupWindow(this);

        tv_hello.setClickable(true);
        tv_hello.setEnabled(true);
        setSpan(tv_hello.getText().toString());
        //tv_hello.setText(setSpan(tv_hello.getText().toString()));
        String uri = "https://101.204.239.166/goldenBowl/rest/ioImage/bannner/5233375f791d4dd1816e5263fd470f73.png?userId=11069&token=6c140b78ca5c3746deff7fabf94fab8d";
        //String uri = "https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png";
        Glide.with(this)
                .load("")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.bg_main_bottom)
                .error(new BitmapDrawable(BitmapUtils.decodeSampledBitmapFromResource(getResources(),
                        R.drawable.bg_main_bottom, 100, 62)))
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

        long length = BitmapUtils.getBitmapSize(BitmapFactory.decodeResource(getResources(), R.drawable.bg_main_bottom));
        long length2 = BitmapUtils.getBitmapSize(BitmapUtils.decodeSampledBitmapFromResource(getResources(),
                R.drawable.bg_main_bottom, 100, 62));
        tv_hello.setText(length + "," + length2);

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        // EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void handleDrawable() {
        TransitionDrawable transitionDrawable = (TransitionDrawable) tv_hello.getBackground();
        transitionDrawable.startTransition(1500);
//        RotateDrawable rotateDrawable=(RotateDrawable) ivDrawable.getDrawable();
//        rotateDrawable.setLevel(10000);
        ivDrawable.getDrawable().mutate().setAlpha(25);
    }

    private void setWindmill() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ObjectAnimator anim = ((ObjectAnimator) ivDrawable2.getTag());
                if (anim == null) return;
                switch (i) {
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            anim.pause();
                            anim.setDuration(400);
                            anim.resume();
                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            anim.pause();
                            anim.setDuration(200);
                            anim.resume();
                        }
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            anim.pause();
                            anim.setDuration(50);
                            anim.resume();
                        }
                        break;
                }
            }
        });
    }

    private void setSpan(String content) {
        SpannableString spannableString = new SpannableString(content);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableString.setSpan(new RelativeSizeSpan(2.0f), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(spannableString).setSpan(new UnderlineSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_hello.setText(spannableString);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            WindowUtil.hidePopupWindow();
        }
        return super.onKeyDown(keyCode, event);
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

    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, 111);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FloatWindoeEvent floatWindoeEvent) {
        WindowUtil.showPopupWindow(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HideFloatWindowEvent hideFloatWindowEvent) {
        WindowUtil.hidePopupWindow();
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

    @OnClick(R.id.btn_on_off)
    public void onViewClicked() {
        turnOnOff();
    }

    @SuppressWarnings("ResourceType")
    void turnOnOff() {
        Animator rotate = AnimatorInflater.loadAnimator(this, R.anim.roate_windmill_animator);
        rotate.setInterpolator(new AccelerateInterpolator());
        rotate.setTarget(ivDrawable2);

        final ObjectAnimator anim = ObjectAnimator.ofFloat(ivDrawable2, "rotation", 0f, 360f);
        anim.setDuration(500);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);

        final ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivDrawable2, "rotation", 0f, 360f);
        anim2.setDuration(1000);
        anim2.setInterpolator(new DecelerateInterpolator());

        if (ivDrawable2.getTag() == null) {
            btnOnOff.setText(getSpanText(true));
            rotate.start();
            rotate.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    anim.start();
                    ivDrawable2.setTag(anim);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        } else {
            final ObjectAnimator animator2 = ((ObjectAnimator) ivDrawable2.getTag());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (animator2.isPaused()) {
                    btnOnOff.setText(getSpanText(true));
                    rotate.start();
                    rotate.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                                animator2.resume();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                } else {
                    btnOnOff.setText(getSpanText(false));
                    animator2.pause();
                    anim2.start();
                }
            }
        }
    }

    SpannableString getSpanText(boolean turnOn) {
        SpannableString spannableString = new SpannableString(btnOnOff.getText().toString());
        if (turnOn) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), 3, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), 3, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    private Executor getThreadPoolExecutor() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>(128);
        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue,
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NonNull Runnable runnable) {
                        return new Thread(runnable, "myThread");
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

                    }
                });
        return executorService;
    }

}
