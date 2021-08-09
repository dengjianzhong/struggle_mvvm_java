package com.struggle.base.utils;

import android.net.ParseException;
import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;
import com.struggle.base.app.constants.AppConstants;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import retrofit2.HttpException;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/9 14:17
 * @Description TODO
 */
public class NetUtil {
    /**
     * 统一处理异常信息
     *
     * @param e
     * @return
     */
    public static String analyzeException(Throwable e) {
        String errMsg;
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case AppConstants.UNAUTHORIZED:
                case AppConstants.FORBIDDEN:
                case AppConstants.NOT_FOUND:
                    errMsg = "网络错误";
                    break;
                case AppConstants.INTERNAL_SERVER_ERROR:
                case AppConstants.BAD_GATEWAY:
                case AppConstants.GATEWAY_TIMEOUT:
                    errMsg = "服务器错误";
                    break;
            }
        }

        if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException || e instanceof MalformedJsonException) {
            errMsg = "结果解析异常";
        } else if (e instanceof ConnectException) {
            errMsg = "网络连接失败";
        } else if (e instanceof SSLException) {
            errMsg = "证书验证失败";
        } else if (e instanceof ConnectTimeoutException || e instanceof SocketTimeoutException) {
            errMsg = "网络连接超时";
        } else if (e instanceof UnknownHostException) {
            errMsg = "网络连接不可用";
        } else {
            errMsg = e.getMessage();
        }

        return errMsg;
    }
}
