package com.zhong.struggle_mvvm.logic.http;

import com.struggle.base.app.bean.DataResponse;
import com.zhong.struggle_mvvm.logic.bean.ArticleDetailBean;
import com.zhong.struggle_mvvm.logic.bean.ClassifyBean;
import com.zhong.struggle_mvvm.logic.bean.TestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("categories/GanHuo")
    Observable<DataResponse<List<TestBean>>> requestGanHuo();

    @GET("categories/Article")
    Observable<DataResponse<List<TestBean>>> requestArticle();

    @GET("post/{id}")
    Observable<DataResponse<ArticleDetailBean>> requestArticleDetail(@Path("id") String id);

    @GET("categories/Girl")
    Observable<DataResponse<List<ClassifyBean>>> requestGirl();
}
