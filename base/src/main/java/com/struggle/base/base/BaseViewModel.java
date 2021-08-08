package com.struggle.base.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.struggle.base.app.bean.DataResponse;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 15:18
 * @Description TODO
 */
public abstract class BaseViewModel extends ViewModel {
    /**
     * 加载错误相关信息
     */
    public MutableLiveData<DataResponse<Object>> messageLiveData = new MutableLiveData();

    /**
     * 显示/隐藏加载弹窗
     * <p>
     * true 显示加载弹窗
     * false 隐藏加载弹窗
     */
    public MutableLiveData<Boolean> dialogLiveData = new MutableLiveData();
}
