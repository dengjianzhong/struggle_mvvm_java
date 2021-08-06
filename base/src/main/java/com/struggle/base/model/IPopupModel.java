package com.struggle.base.model;

import androidx.annotation.LayoutRes;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 14:06
 * @Description TODO
 */
public interface IPopupModel {

    @LayoutRes
    int getLayoutId();

    default void initView() {
    }

    default void initData() {
    }

    default void initEvent() {
    }

    /**
     * 是否开启EventBus事件总线
     * <p>
     * false 默认关闭
     */
    default boolean userEventBus() {
        return false;
    }

    /**
     * 设置PopupWindow相关参数
     */
    int getWindowWidth();

    int getWindowHeight();

    int getWindowElevation();
}
