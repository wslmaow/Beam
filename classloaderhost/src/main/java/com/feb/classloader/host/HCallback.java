package com.feb.classloader.host;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class HCallback implements Handler.Callback {//实现Callback接口

    public static final int LAUNCH_ACTIVITY = 100;

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case LAUNCH_ACTIVITY://启动事件
                Object obj = msg.obj;
                try {
                    Intent intent = (Intent) FieldUtil.getField(obj.getClass(), obj, "intent");//拿到ActivityClientRecord的intent字段
                    Intent targetIntent = intent.getParcelableExtra(HookHelper.TRANSFER_INTENT);//拿到我们要启动PluginActivity的Intent
                    if (targetIntent == null){
                        intent.setComponent(new ComponentName("com.feb.classloader.plugin", "com.feb.classloader.plugin.PluginActivity"));
                    }else {
                        intent.setComponent(targetIntent.getComponent());//替换为启动PluginActivity
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
        return false;
    }
}

