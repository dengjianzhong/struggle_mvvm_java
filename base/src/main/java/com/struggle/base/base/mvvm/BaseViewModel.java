package com.struggle.base.base.mvvm;

import androidx.lifecycle.ViewModel;

import com.struggle.base.utils.ClassUtil;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:18
 * @Description TODO
 */
public abstract class BaseViewModel<T extends BaseRepository> extends ViewModel {
    /**创建API请求对象*/
    public T rep;
    {
        Class<T> apiClass = (Class<T>) ClassUtil.getParentGeneric(this, 0);
        try {
            rep = apiClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

