package com.feb.demo;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.feb.demo.utils.RetrofitUtils;

import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(RetrofitUtils.getOkHttpClient()));
    }
}
