package com.hua.goddog.global;

import android.app.Application;

/**
 * Created by hzw on 2016/8/23.
 */
public class GApplication extends Application {

    public static GApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
