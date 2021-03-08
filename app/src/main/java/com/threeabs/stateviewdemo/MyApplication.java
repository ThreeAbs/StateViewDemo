package com.threeabs.stateviewdemo;

import android.app.Application;

/**
 * Author: HD on 2021/3/8
 * Email: zhanghd@aura.cn
 * Describe:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StateViewManager.getInstance().initErrorID(R.layout.layout_error);
    }
}
