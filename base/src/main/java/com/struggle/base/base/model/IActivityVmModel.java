package com.struggle.base.base.model;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:39
 * @Description TODO
 */
public interface IActivityVmModel {
    void initViewModel();

    void initLiveData();

    default void observer(){

    }

    void initBinding();
}
