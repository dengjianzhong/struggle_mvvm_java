package com.struggle.base.app.constants;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 14:29
 * @Description TODO
 */
public interface AppConstants {
    /**返回成功状态码*/
    int SUCCESS_CODE = 100;

    /**网络错误*/
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;

    /**服务器错误*/
    int INTERNAL_SERVER_ERROR = 500;
    int BAD_GATEWAY = 502;
    int GATEWAY_TIMEOUT = 504;
}
