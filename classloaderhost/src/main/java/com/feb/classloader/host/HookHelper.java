package com.feb.classloader.host;

import android.os.Build;
import android.os.Handler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookHelper {
    public static final String TRANSFER_INTENT = "transfer_intent";

    public static void hookAMS() throws Exception {
        Object singleton = null;
        if (Build.VERSION.SDK_INT >= 26) {//大于等于8.0
            Class<?> clazz = Class.forName("android.app.ActivityManager");
            singleton = FieldUtil.getField(clazz, null, "IActivityManagerSingleton");//拿到静态字段
        } else {//8.0以下
            Class<?> activityManagerNativeClazz = Class.forName("android.app.ActivityManagerNative");
            singleton = FieldUtil.getField(activityManagerNativeClazz, null, "gDefault");//拿到静态字段
        }
        Class<?> singleClazz = Class.forName("android.util.Singleton");
        Method getMethod = singleClazz.getMethod("get");
        Object iActivityManager = getMethod.invoke(singleton);//拿到AMS
        Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManagerClazz}, new IActivityManagerProxy(iActivityManager));//生成动态代理
        FieldUtil.setField(singleClazz, singleton, "mInstance", proxy);//将代理后的对象设置回去
    }

    public static void hookH() throws Exception {
        Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClazz.getDeclaredMethod("currentActivityThread");
        Object activityThread = currentActivityThreadMethod.invoke(null);//拿到activityThread

        Object originalInstrumentation = FieldUtil.getField(activityThreadClazz,activityThread,"mInstrumentation");
        FieldUtil.setField(activityThreadClazz,activityThread,"mInstrumentation",new EvilInstrumentation(originalInstrumentation));

        Object mH = FieldUtil.getField(activityThreadClazz, activityThread, "mH");//拿到mH
        FieldUtil.setField(Handler.class, mH, "mCallback", new HCallback());//给mH设置callback
    }


}

