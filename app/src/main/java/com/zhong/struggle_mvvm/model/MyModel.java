package com.zhong.struggle_mvvm.model;

import androidx.lifecycle.MutableLiveData;

import com.struggle.base.base.vm.BaseViewModel;
import com.struggle.base.http.observer.OnCompleteListener;
import com.zhong.struggle_mvvm.bean.ArticleDetailBean;
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

    public MutableLiveData<ArticleDetailBean> articleDetail = new MutableLiveData<>();

    /**
     * 获取干货所有子分类
     */
    public void requestGanHuo() {
        launch(api.requestArticle(), testBeans -> {
            ganHuoLiveData.postValue(testBeans);
        });
    }


    /**
     * 获取文章详情
     *
     * @param id
     */
    public void requestArticleDetail(String id) {
        launch(api.requestArticleDetail(id), articleDetailBean -> {
            articleDetail.postValue(articleDetailBean);
        });
    }
}
