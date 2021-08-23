package com.struggle.base.http.observer;

import com.struggle.base.app.bean.DataResponse;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:37
 * @Description TODO
 */
public interface OnSubscribeListener<T> {
    void onSuccess(T t);

    /**
     * 需要时手动复写
     *
     * @param bean
     */
    default void onError(DataResponse bean) {
    }
}
