package com.struggle.base.base.plugins;

import androidx.annotation.LayoutRes;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 14:06
 * @Description TODO
 */
public interface PopupPlugin {

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

    //设置暗影体现出立体
    int getWindowElevation();

    //设置点击外部是否可以取消
    boolean isCanTCancel();
}
