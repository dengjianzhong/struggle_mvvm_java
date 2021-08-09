package com.zhong.struggle_mvvm.model;

import androidx.lifecycle.MutableLiveData;

import com.struggle.base.base.vm.BaseViewModel;
import com.zhong.struggle_mvvm.bean.TestBean;
import com.zhong.struggle_mvvm.http.ApiService;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 16:05
 * @Description TODO
 */
public class MyModel extends BaseViewModel<ApiService> {

    public MutableLiveData<List<TestBean>> ganHuoLiveData = new MutableLiveData<>();

    public void requestGanHuo() {
        launch(api.postGanHuo(), testBeans -> {
            ganHuoLiveData.postValue(testBeans);
        });
    }
}
