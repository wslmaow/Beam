package com.feb.classloader.host;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.Method;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("app","onCreate");
    }

    private Resources pluginResource = null;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            InjectUtil.inject(this, getClassLoader());
            //HookHelper.hookAMS();
            HookHelper.hookH();
            initPluginResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPluginResource() throws Exception {
        Class<AssetManager> clazz = AssetManager.class;
        AssetManager assetManager = clazz.newInstance();//创建AssetManager
        Method method = clazz.getMethod("addAssetPath", String.class);//拿到addAssetPath方法
        method.invoke(assetManager, getExternalFilesDir("plugin").listFiles()[0].getAbsolutePath());//调用addAssetPath传入插件APk路径
        pluginResource = new Resources(assetManager, getResources().getDisplayMetrics(), getResources().getConfiguration());//生成插件Resource }
    }

    @Override
    public Resources getResources() {
        return pluginResource==null?super.getResources():pluginResource;
    }
}
