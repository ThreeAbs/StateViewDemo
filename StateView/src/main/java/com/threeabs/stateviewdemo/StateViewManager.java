package com.threeabs.stateviewdemo;

/**
 * Author: HD on 2021/3/8
 * Email: zhanghd@aura.cn
 * Describe:设置默认视图
 */
public class StateViewManager {

    /**
     * 加载默认布局
     */
    public  int DEFAULT_LOADING_ID = ViewConfigure.defaultID;
    /**
     * 空界面默认布局
     */
    public  int DEFAULT_ERROR_ID = ViewConfigure.defaultID;
    /**
     * 错误界面默认布局
     */
    public  int DEFAULT_EMPTY_ID = ViewConfigure.defaultID;


    public static StateViewManager manager;

    private StateViewManager() {
    }

    public static StateViewManager getInstance() {
        if (manager == null) {
            synchronized (StateViewManager.class) {
                if (manager == null) {
                    manager = new StateViewManager();
                }
            }
        }
        return manager;

    }

    public void init(int loadID, int emptyID, int errorID) {
        initLoadingID(loadID);
        initEmptyID(emptyID);
        initErrorID(errorID);
    }

    public void initLoadingID(int loadID) {
        DEFAULT_LOADING_ID = loadID;
    }

    public void initEmptyID(int emptyID) {
        DEFAULT_EMPTY_ID = emptyID;
    }

    public void initErrorID(int errorID) {
        DEFAULT_ERROR_ID = errorID;
    }

}
