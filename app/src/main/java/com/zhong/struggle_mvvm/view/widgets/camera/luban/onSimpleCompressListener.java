package com.zhong.struggle_mvvm.view.widgets.camera.luban;

import top.zibin.luban.OnCompressListener;

/**
 * @Author 邓建忠
 * @CreateTime 2022/2/18 9:42
 * @Description TODO
 */
public interface onSimpleCompressListener extends OnCompressListener {
    @Override
    default void onStart() {

    }

    @Override
    default void onError(Throwable e) {

    }
}
