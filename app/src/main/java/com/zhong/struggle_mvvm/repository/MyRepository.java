package com.zhong.struggle_mvvm.repository;

import com.struggle.base.base.mvvm.BaseRepository;
import com.struggle.base.http.observer.OnSubscribeListener;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.bean.TestBean;
import com.zhong.struggle_mvvm.http.ApiService;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/13 15:04
 * @Description TODO
 */
public class MyRepository extends BaseRepository<ApiService> {
    /**
     * 获取干货所有子分类
     */
    public void requestGanHuo(OnSubscribeListener<List<TestBean>> listener) {
        launch(api.requestArticle(), listener);
    }

    /**
     * 获取文章详情
     *
     * @param id
     */
    public void requestArticleDetail(String id, OnSubscribeListener<ArticleDetailBean> listener) {
        launch(api.requestArticleDetail(id), listener);
    }
}