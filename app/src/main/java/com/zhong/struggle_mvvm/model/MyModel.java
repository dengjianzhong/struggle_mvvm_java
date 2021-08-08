package com.zhong.struggle_mvvm.model;

import com.struggle.base.base.BaseViewModel;
import com.struggle.base.bean.DataResponse;
import com.struggle.base.launcher.IOHandler;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 16:05
 * @Description TODO
 */
public class MyModel extends BaseViewModel {
    public void postNet() {
        IOHandler.postWork(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            DataResponse<Object> response = new DataResponse<>();
            response.setMessage("加载失败");
            messageLiveData.postValue(response);
        });
    }
}
