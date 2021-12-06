package com.zhong.struggle_mvvm.logic.mvp.model;

import com.struggle.base.base.mvp.BaseModel;
import com.struggle.base.http.observer.OnSubscribeListener;
import com.zhong.struggle_mvvm.logic.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.logic.http.ApiService;
import com.zhong.struggle_mvvm.logic.mvp.contract.MyContract;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 14:27
 * @Description TODO
 */
public class MyMvpModel extends BaseModel<ApiService> implements MyContract.Model {
    @Override
    public void requestArticleDetail(String id, OnSubscribeListener<ArticleDetailBean> listener) {
        launch(api.requestArticleDetail(id), "拼命加载中...", listener);
    }
}
