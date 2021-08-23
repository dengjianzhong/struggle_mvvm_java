package com.struggle.base.base.mvp.impl;

public interface IPresenter<V extends IView> {
    /**
     * 与P层绑定
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 断开V层和P层
     * 在Activity的onDestroy()中调用
     */
    void detachView();

    boolean isAttachView();
}
