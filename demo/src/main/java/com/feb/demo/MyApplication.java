package com.feb.demo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.feb.demo.utils.RetrofitUtils;
import com.feb.demo.utils.WindowUtil;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/24.
 */

public class MyApplication extends Application {
    public int started=0;
    public int resumed=0;
    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(RetrofitUtils.getOkHttpClient()));
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());

    }


    class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            started++;

        }

        @Override
        public void onActivityResumed(Activity activity) {
            resumed++;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (Settings.canDrawOverlays(getApplicationContext())){
                    //WindowUtil.showPopupWindow(MyApplication.this);
                }else {
                    checkDrawOverlayPermission(activity);
                }
            }
        }

        public void checkDrawOverlayPermission(Activity activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)return;
            /** check if we already  have permission to draw over other apps */
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                /** if not construct intent to request permission */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                /** request permission via start activity for result */
                activity.startActivityForResult(intent, 111);
            }
        }
        @Override
        public void onActivityPaused(Activity activity) {
            resumed--;

        }

        @Override
        public void onActivityStopped(Activity activity) {
            started--;
            if (resumed==0){
                WindowUtil.hidePopupWindow();
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
