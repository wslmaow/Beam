package com.feb.media.utils;

/**
 * Created by Administrator on 2018/3/16.
 */

public class TimeUtil {
    public static String getTimeFromInt(int duration){
        int seconds = duration/1000;
        int minutes = seconds/60;
        int hours = minutes/60;
        return hours+":"+minutes%60+"'"+seconds%60+"''";
    }
}
