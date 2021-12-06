package com.struggle.base.base.plugins;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 14:06
 * @Description TODO
 */
public interface DialogPlugin {

    default void initView() {
    }

    default void initData() {
    }

    default void initEvent() {
    }

    int getWidth();

    int getHeight();

    int getGravity();

    /**
     * 默认缩放动画
     *
     * @return
     */
    default int getAnimationStyle() {
        return AnimPlugin.ANIM_IOS;
    }

    /**
     * 是否开启EventBus事件总线
     * <p>
     * false 默认关闭
     */
    default boolean userEventBus() {
        return false;
    }
}
