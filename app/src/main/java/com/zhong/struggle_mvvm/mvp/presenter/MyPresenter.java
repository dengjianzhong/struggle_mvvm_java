package com.zhong.struggle_mvvm.mvp.presenter;

import com.struggle.base.app.bean.DataResponse;
import com.struggle.base.base.mvp.BasePresenter;
import com.struggle.base.http.observer.OnSubscribeListener;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.mvp.contract.MyContract;
import com.zhong.struggle_mvvm.mvp.model.MyMvpModel;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 14:22
 * @Description TODO
 */
public class MyPresenter extends BasePresenter<MyContract.View, MyMvpModel> implements MyContract.Presenter {
    @Override
    public void requestArticleDetail(String id) {
        model.requestArticleDetail(id, new OnSubscribeListener<ArticleDetailBean>() {
            @Override
            public void onSuccess(ArticleDetailBean bean) {
                mView.onArticleDetail(bean);
            }

            @Override
            public void onError(DataResponse bean) {
                mView.onMessage(bean.getMessage());
            }
        });
    }
}
