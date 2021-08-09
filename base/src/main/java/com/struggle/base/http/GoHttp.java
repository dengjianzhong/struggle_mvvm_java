package com.struggle.base.http;

import com.struggle.base.app.constants.ApiConstants;
import com.struggle.base.http.interceptor.CommonInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/5 11:43
 * @Description TODO
 */
public class GoHttp {
    //默认超时时间
    private final int DEFAULT_TIMEOUT = 120;
    //网络请求客户端
    private Retrofit retrofit;
    private static GoHttp goHttp;

    public static GoHttp Instance() {
        if (goHttp == null) {
            synchronized (GoHttp.class) {
                if (goHttp == null) {
                    goHttp = new GoHttp();
                }
            }
        }
        return goHttp;
    }

    private GoHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new CommonInterceptor())
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 创建由 {@code service} 接口定义的 API 端点的实现
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clz) {
        return retrofit.create(clz);
    }
}
