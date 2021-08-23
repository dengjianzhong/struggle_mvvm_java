package com.zhong.struggle_mvvm.mvp.contract;

import com.struggle.base.base.mvp.impl.IView;
import com.struggle.base.http.observer.OnSubscribeListener;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/23 14:29
 * @Description TODO
 */
public interface MyContract {
    interface View extends IView {
        void onArticleDetail(ArticleDetailBean bean);
    }

    interface Presenter {
        void requestArticleDetail(String id);
    }

    interface Model {
        void requestArticleDetail(String id, OnSubscribeListener<ArticleDetailBean> listener);
    }
}
