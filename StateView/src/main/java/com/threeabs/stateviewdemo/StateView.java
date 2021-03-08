package com.threeabs.stateviewdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：HD on 2020/7/3 09:25
 * 邮箱：zhanghd@aura.cn
 * 说明：状态视图管理器
 * 无需侵入布局为Activity，Fragment，ViewGroup设置Loading，Error，Empty识图
 */
public class StateView {

    /**
     * 点击事件
     */
    private StateViewOnClickListener clickListener;

    /**
     * 使用StateView的类--用于获取注解中的值
     */
    private Class ascriptionClass;

    /**
     * 当前页面全部View
     */
    private List<View> childViewList;

    /**
     * 顶级布局
     */
    private View rootView;

    /**
     * 承装StateView的parent布局
     */
    private ViewGroup parentView;

    /**
     * 加载视图
     */
    private View loadingView;

    /**
     * 错误视图
     */
    private View errorView;

    /**
     * 空试图
     */
    private View emptyView;


    private StateView() {
    }

    public static StateView getInstance() {
        return new StateView();
    }

    @NonNull
    @UiThread
    public StateView bind(@NonNull Activity target) {
        return bind(target, target.getWindow().getDecorView(), null);
    }

    @NonNull
    @UiThread
    public StateView bind(@NonNull Object target, @NonNull View source) {
        return bind(target, source, null);
    }

    @NonNull
    @UiThread
    public StateView bind(@NonNull Object target, @NonNull View source, StateViewOnClickListener clickListener) {
        this.ascriptionClass = target.getClass();
        this.clickListener = clickListener;
        this.rootView = source;

        findParentAndCreateChildViewList();

        return this;
    }

    private void findParentAndCreateChildViewList() {
        int parentID = ViewConfigureHandle.obtainParentID(ascriptionClass);

        if (parentID == ViewConfigure.defaultID) {
            throw new RuntimeException("请在ViewConfigure配置parentID");
        }

        parentView = rootView.findViewById(parentID);

        if (parentView == null) {
            throw new RuntimeException("parentID不存在");
        }

        childViewList = new ArrayList<>();

        int childCount = parentView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            childViewList.add(parentView.getChildAt(i));
        }
    }


    public void showLoad() {
        if (loadingView != null) {
            show(View.GONE, View.VISIBLE, View.GONE, View.GONE);
            return;
        }

        if (ViewConfigureHandle.obtainLoadingID(ascriptionClass) != ViewConfigure.defaultID) {
            showLoad(ViewConfigureHandle.obtainLoadingID(ascriptionClass));
            return;
        }

        if (StateViewManager.getInstance().DEFAULT_LOADING_ID != ViewConfigure.defaultID) {
            showLoad(StateViewManager.getInstance().DEFAULT_LOADING_ID);
            return;
        }
    }


    public void showLoad(@LayoutRes int loadingID) {
        loadingView = LayoutInflater.from(parentView.getContext()).inflate(loadingID, parentView, false);
        parentView.removeView(loadingView);
        parentView.addView(loadingView);
        show(View.GONE, View.VISIBLE, View.GONE, View.GONE);

        {
            loadingView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.onLoadingClick();
                }
            });
        }
    }

    public void showEmpty() {
        if (emptyView != null) {
            show(View.GONE, View.GONE, View.VISIBLE, View.GONE);
            return;
        }

        if (ViewConfigureHandle.obtainEmptyID(ascriptionClass) != ViewConfigure.defaultID) {
            showEmpty(ViewConfigureHandle.obtainEmptyID(ascriptionClass));
            return;
        }

        if (StateViewManager.getInstance().DEFAULT_EMPTY_ID != ViewConfigure.defaultID) {
            showEmpty(StateViewManager.getInstance().DEFAULT_EMPTY_ID);
            return;
        }
    }


    public void showEmpty(@LayoutRes int emptyID) {
        emptyView = LayoutInflater.from(parentView.getContext()).inflate(emptyID, parentView, false);
        parentView.removeView(emptyView);
        parentView.addView(emptyView);
        show(View.GONE, View.GONE, View.VISIBLE, View.GONE);

        {
            emptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.onEmptyClick();
                }
            });
        }
    }

    public void showError() {
        if (errorView != null) {
            show(View.GONE, View.GONE, View.GONE, View.VISIBLE);
            return;
        }

        if (ViewConfigureHandle.obtainErrorID(ascriptionClass) != ViewConfigure.defaultID) {
            showError(ViewConfigureHandle.obtainErrorID(ascriptionClass));
            return;
        }

        if (StateViewManager.getInstance().DEFAULT_ERROR_ID != ViewConfigure.defaultID) {
            showError(StateViewManager.getInstance().DEFAULT_ERROR_ID);
            return;
        }
    }


    public void showError(@LayoutRes int errorID) {
        errorView = LayoutInflater.from(parentView.getContext()).inflate(errorID, parentView, false);
        parentView.removeView(errorView);
        parentView.addView(errorView);
        show(View.GONE, View.GONE, View.GONE, View.VISIBLE);

        {
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.onErrorClick();
                }
            });
        }
    }


    public void showContent() {
        show(View.VISIBLE, View.GONE, View.GONE, View.GONE);
    }

    /**
     * 控制显示隐藏
     *
     * @param contentViewVisibility content内容
     * @param loadingViewVisibility 加载视图
     * @param emptyViewVisibility   空界面
     * @param errorViewVisibility   错误界面
     */
    private void show(int contentViewVisibility, int loadingViewVisibility, int emptyViewVisibility, int errorViewVisibility) {
        for (View view : childViewList) {
            view.setVisibility(contentViewVisibility);
        }
        if (loadingView != null) {
            loadingView.setVisibility(loadingViewVisibility);
        }
        if (errorView != null) {
            errorView.setVisibility(errorViewVisibility);
        }
        if (emptyView != null) {
            emptyView.setVisibility(emptyViewVisibility);
        }
    }

    public void unBind() {
        if (parentView != null) {
            parentView = null;
        }
        if (rootView != null) {
            rootView = null;
        }
        if (childViewList != null) {
            childViewList = null;
        }
        if (clickListener != null) {
            clickListener = null;
        }
    }


    public interface StateViewOnClickListener {

        void onLoadingClick();

        void onErrorClick();

        void onEmptyClick();
    }

}
