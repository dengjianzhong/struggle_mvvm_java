package com.zhong.struggle_mvvm.logic.mvvm.model;

import androidx.lifecycle.MutableLiveData;

import com.struggle.base.base.mvvm.BaseViewModel;
import com.zhong.struggle_mvvm.logic.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.logic.bean.ClassifyBean;
import com.zhong.struggle_mvvm.logic.bean.TestBean;
import com.zhong.struggle_mvvm.logic.mvvm.repository.MyRepository;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 16:05
 * @Description TODO
 */
public class MyModel extends BaseViewModel<MyRepository> {

    //干货所有子分类
    public MutableLiveData<List<TestBean>> ganHuoLiveData = new MutableLiveData<>();

    //文章详情
    public MutableLiveData<ArticleDetailBean> articleDetail = new MutableLiveData<>();

    //分类
    public MutableLiveData<List<ClassifyBean>> classify = new MutableLiveData<>();

    /**
     * 获取干货所有子分类
     */
    public void requestGanHuo() {
        rep.requestGanHuo(testBeans -> ganHuoLiveData.setValue(testBeans));
    }


    /**
     * 获取文章详情
     *
     * @param id
     */
    public void requestArticleDetail(String id) {
        rep.requestArticleDetail(id, articleDetailBean -> articleDetail.setValue(articleDetailBean));
    }

    /**
     * 获取分类
     */
    public void requestGirl() {
        rep.requestGirl(classifyBeans -> classify.setValue(classifyBeans));
    }
}
