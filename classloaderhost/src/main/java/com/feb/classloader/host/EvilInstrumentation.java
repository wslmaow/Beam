package com.feb.classloader.host;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class EvilInstrumentation extends Instrumentation {
    private Object instrumentation;
    public EvilInstrumentation(Object instrumentation) {
        this.instrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        try {
            Log.e("apk","execStartActivity");
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class,
                    IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            Intent newIntent = new Intent();//创建新的Intent
            newIntent.setClassName("com.feb.classloader.plugin", "com.feb.classloader.plugin.PluginActivity");//启动目标SubActivity
            intent.putExtra(HookHelper.TRANSFER_INTENT, newIntent);//保留原始intent
            return (ActivityResult)execStartActivity.invoke(instrumentation, who, contextThread, token, target,
                    intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
