package com.threeabs.stateviewdemo;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: HD on 2021/3/8
 * Email: zhanghd@aura.cn
 * Describe:状态视图布局位置
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewConfigure {


    /**
     * 默认的ID
     */
    int defaultID = -1;

    /**
     * 承装StateView的父布局ID
     */
    @IdRes int parentID() default defaultID;

    /**
     * Loading页面的布局ID
     */
    @LayoutRes int loadingID() default defaultID;

    /**
     * 空界面的布局ID
     */
    @LayoutRes int emptyID() default defaultID;

    /**
     * 错误界面的布局ID
     */
    @LayoutRes int errorID() default defaultID;
}
