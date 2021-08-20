package com.struggle.base.base.model;

import androidx.annotation.LayoutRes;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 14:06
 * @Description TODO
 */
public interface ViewModule {

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
     * 禁止横屏
     * <p>
     * true 默认开启
     */
    default boolean disableHorizontalScreen() {
        return true;
    }
}
