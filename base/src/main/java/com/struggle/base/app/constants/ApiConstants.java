package com.struggle.base.app.constants;


import com.struggle.base.BuildConfig;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 10:32
 * @Description TODO
 */
public interface ApiConstants {
    String BASE_URL = BuildConfig.IS_BADGE ? "https://gank.io/api/v2/" : "";
}
