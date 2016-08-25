package com.android.app.touristguide.util;

import android.util.Log;

public class LogUtil {

    public static void debug(String tag,String message){
        Log.d(tag,message);
    }
    public static void error(String tag,String message){
        Log.e(tag,message);
    }
    public static void info(String tag,String message){
        Log.i(tag,message);
    }
}
