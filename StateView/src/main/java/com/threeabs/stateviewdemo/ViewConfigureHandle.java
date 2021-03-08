package com.threeabs.stateviewdemo;


/**
 * @author ZhangHongDa
 * @date 2019/3/1
 * email: 1209698993@qq.com
 * describe:EventTransfer注解处理类
 */

public class ViewConfigureHandle {



    /**
     * 是否开启了StateView
     *
     * @param o
     * @return
     */
    public static boolean onEnable(Class o) {
        ViewConfigure annotation = (ViewConfigure) o.getAnnotation(ViewConfigure.class);
        return annotation != null;
    }

    /**
     * 获取parentID
     *
     * @param o
     * @return
     */
    public static int obtainParentID(Class o) {
        ViewConfigure annotation = (ViewConfigure) o.getAnnotation(ViewConfigure.class);
        return annotation == null ? ViewConfigure.defaultID : annotation.parentID();
    }

    /**
     * 获取加载视图ID
     *
     * @param o
     * @return
     */
    public static int obtainLoadingID(Class o) {
        ViewConfigure annotation = (ViewConfigure) o.getAnnotation(ViewConfigure.class);
        return annotation == null ? ViewConfigure.defaultID : annotation.loadingID();
    }

    /**
     * 获取错误界面视图
     *
     * @param o
     * @return
     */
    public static int obtainErrorID(Class o) {
        ViewConfigure annotation = (ViewConfigure) o.getAnnotation(ViewConfigure.class);
        return annotation == null ? ViewConfigure.defaultID : annotation.errorID();
    }

    /**
     * 获取空界面视图
     *
     * @param o
     * @return
     */
    public static int obtainEmptyID(Class o) {
        ViewConfigure annotation = (ViewConfigure) o.getAnnotation(ViewConfigure.class);
        return annotation == null ? ViewConfigure.defaultID : annotation.emptyID();
    }

}
