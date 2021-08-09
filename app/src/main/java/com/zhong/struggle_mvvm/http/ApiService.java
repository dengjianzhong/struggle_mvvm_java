package com.zhong.struggle_mvvm.http;

import com.struggle.base.app.bean.DataResponse;
import com.zhong.struggle_mvvm.bean.TestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("categories/GanHuo")
    Observable<DataResponse<List<TestBean>>> postGanHuo();
}
