package com.zhong.struggle_mvvm.logic.mvp.presenter;

import com.struggle.base.base.mvp.BasePresenter;
import com.zhong.struggle_mvvm.logic.mvp.contract.MyContract;
import com.zhong.struggle_mvvm.logic.mvp.model.MyMvpModel;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 14:22
 * @Description TODO
 */
public class MyPresenter extends BasePresenter<MyContract.View, MyMvpModel> implements MyContract.Presenter {
    @Override
    public void requestArticleDetail(String id) {
        model.requestArticleDetail(id, bean -> mView.onArticleDetail(bean));
    }
}
